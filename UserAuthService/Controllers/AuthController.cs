using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using UserAuthService.DTOs;
using UserAuthService.Entities;
using UserAuthService.Services;

    namespace UserAuthService.Controllers
    {
        [Route("[controller]")]
        [ApiController]
        public class AuthController(IAuthService authService) : ControllerBase
        {
            public static User user = new();

            [HttpPost("register")]
            public async Task<ActionResult<User>> Regiter(UserDTO userDTO)
            {
                var user = await authService.RegisterAsync(userDTO);

               if(user == null)
                {
                    return BadRequest("User with Email " +userDTO.Email+ " already exists!!!");
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
