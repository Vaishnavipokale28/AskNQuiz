using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using UserAuthService.DTOs;
using UserAuthService.Entities;
using UserAuthService.Services;

namespace UserAuthService.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class AuthController : ControllerBase
    {
        private readonly IAuthService _authService;
        private readonly IHttpClientFactory _httpClientFactory;
        private readonly IConfiguration _config;

        public AuthController(IAuthService authService, IHttpClientFactory httpClientFactory, IConfiguration config)
        {
            _authService = authService;
            _httpClientFactory = httpClientFactory;
            _config = config;
        }

        [HttpPost("register")]
        public async Task<ActionResult<User>> Register([FromBody] UserDTO userDTO)
        {
            // 1) Create user in .NET DB
            var user = await _authService.RegisterAsync(userDTO);
            if (user == null)
                return BadRequest("User already exists!");

            // 2) Create a matching Student in Spring service
            // Spring base URL is configurable: ServiceUrls:SpringBaseUrl (default http://localhost:8080)
            var client = _httpClientFactory.CreateClient("SpringService");

            var studentDto = new StudentDTO
            {
                Name = string.IsNullOrWhiteSpace(userDTO.Name) ? userDTO.Email.Split('@')[0] : userDTO.Name,
                Email = user.Email,
                Password = userDTO.Password,
                AdmissionDate = DateOnly.FromDateTime(userDTO.AdmissionDate == default ? DateTime.UtcNow : userDTO.AdmissionDate)
            };

            var resp = await client.PostAsJsonAsync("/students/register", studentDto);

            if (!resp.IsSuccessStatusCode)
            {
                // Rollback .NET user if Spring student creation failed
                await _authService.DeleteUserAsync(user.UserId);

                var body = await resp.Content.ReadAsStringAsync();
                return StatusCode(500, $"Failed to create Student in Spring service. Status={(int)resp.StatusCode}. Body={body}");
            }

            return Ok(user);
        }

        [HttpPost("login")]
        public async Task<ActionResult<TokenResponseDTO>> Login([FromBody] UserDTO userDTO)
        {
            var result = await _authService.LoginAsync(userDTO);
            if (result == null)
                return BadRequest("Invalid user credentials!");

            return Ok(result);
        }

        [Authorize]
        [HttpGet]
        public IActionResult AuthenticatedEndpoint()
        {
            return Ok("You are authenticated.");
        }

        [Authorize(Roles = "Admin")]
        [HttpGet("admin")]
        public IActionResult AdminOnlyEndPoint()
        {
            return Ok("You are an admin.");
        }

        [HttpPost("refresh-token")]
        public async Task<ActionResult<TokenResponseDTO>> RefreshToken([FromBody] RefreshTokenRequestDTO dto)
        {
            var result = await _authService.RefreshTokensAsync(dto);

            if (result is null || result.AccessToken is null || result.RefreshToken is null)
                return Unauthorized("Invalid refresh token!");

            return Ok(result);
        }
    }
}
