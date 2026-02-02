using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Net.Http;
using UserAuthService.Clients;
using UserAuthService.DTOs;
using UserAuthService.Entities;
using UserAuthService.Services;

    namespace UserAuthService.Controllers
    {
        [Route("[controller]")]
        [ApiController]
        public class AuthController(IAuthService authService, IStudentServiceClient studentClient) : ControllerBase
        {
            public static User user = new();


        [HttpPost("register")]
        public async Task<ActionResult<User>> Register(UserDTO userDTO)
        {
            // 1. Register User in .NET Database
            var user = await authService.RegisterAsync(userDTO);

            if (user == null)
            {
                return BadRequest("User already exists!!!");
            }

            // 2. Prepare to call Java Service
            var client = httpClientFactory.CreateClient();

            // Data to send to Java (must match StudentDto.java)
            var studentData = new
            {
                email = user.Email,
                // add other fields needed by Java

            };

            // 3. Call Java via POST
            var response = await client.PostAsJsonAsync("http://localhost:8080/students/register", studentData);

            if (!response.IsSuccessStatusCode)
            {
                // Optional: Handle what happens if Java fails (e.g., rollback .NET user)
                return StatusCode(500, "User created, but failed to create Student record in Java service.");
            }

            return Ok(user);
        }

        [HttpPost("login")]
            public async Task<ActionResult<TokenResponseDTO>> Login(UserDTO userDTO)
            {
                var result = await authService.LoginAsync(userDTO);
                if (result == null)
                {
                    return BadRequest("Invalid User Credentials!!!");
                }

                // return JSON object instead of just a string
                return Ok(result);
            }

        // [Authorize] - used to restrict the access ensuring only authenticated and authorized users can access this endpoint 

            [Authorize]
            [HttpGet]
            public IActionResult AuthticatedEndpoint()
            {
                return Ok("You are Authticated....");
            }

            [Authorize(Roles = "Admin")]
            [HttpGet("admin")]
            public IActionResult AdminOnlyEndPoint()
            {
                return Ok("You are an admin....");
            }


            [HttpPost("refresh-token")]
            public async Task<ActionResult<TokenResponseDTO>> RefreshToken([FromBody] RefreshTokenRequestDTO refreshTokenRequestDTO)
            {
                
                var result = await authService.RefreshTokensAsync(refreshTokenRequestDTO);

            if (result is null || result.AccessToken is null || result.RefreshToken is null)
                return Unauthorized("Invalid Refresh Token!!!");

            return Ok(result);

            }
        }
    }
