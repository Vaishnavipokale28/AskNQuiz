using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Cryptography;
using UserAuthService.Data;
using UserAuthService.DTOs;
using UserAuthService.Entities;
using UserAuthService.Helpers;

namespace UserAuthService.Services
{
    public class AuthService(UserAuthDbContext dbContext, IConfiguration config) : IAuthService
    {
        public async Task<TokenResponseDTO?> LoginAsync(UserDTO userDTO)
        {
            // check if given Email exists in database 
            var user = await dbContext.Users.FirstOrDefaultAsync(u => u.Email == userDTO.Email);

            if (user is null)
            {
                // TODO: redirecct to Register()
                return null;
            }

            if (new PasswordHasher<User>().VerifyHashedPassword(user, user.HashedPassword, userDTO.Password) == PasswordVerificationResult.Failed)
            {

                return null;
            }

           
            return await CreateTokenResponse(user); ;

        }

        private async Task<TokenResponseDTO> CreateTokenResponse(User user)
        {
            return new TokenResponseDTO
            {
                AccessToken = GenerateToken(user),
                RefreshToken = await GenerateAndSaveRefreshTokenAsync(user)

            };
        }

        public async Task<User?> RegisterAsync(UserDTO userDTO)
        {
            // check if user with email id as userDTO.Email already exists
            if (await dbContext.Users.AnyAsync(u => u.Email == userDTO.Email))
            {
                return null;
            }

            var user = new User();

            // .Net provided Password hasher
            var hashedPassword = new PasswordHasher<User>()
                .HashPassword(user, userDTO.Password);

            user.Email = userDTO.Email;
            user.HashedPassword = hashedPassword;

            // convert string to enum
            user.Role = Enum.Parse<UserRole>(userDTO.Role, true);


            dbContext.Add(user);
            await dbContext.SaveChangesAsync();

            return user;
            
        }

        private string GenerateToken(User user)
        {
            // Claims are information about the user stored in token 
            // other micrservices can read these claims without making DB calls
            var claims = new List<Claim>
            {
                // Sub - identifies the user the token belongs to 
                new Claim(JwtRegisteredClaimNames.Sub, user.UserId.ToString()),
                new Claim(JwtRegisteredClaimNames.Email, user.Email),

                // jti - uniquely identifies the token 
                new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
                new Claim(ClaimTypes.Role, user.Role.ToString())

            };

            

            // token of 64 characters is needed
            var privateKeyPath = config["JwtKeys:PrivateKeyPath"]
                ?? Environment.GetEnvironmentVariable("JWT_PRIVATE_KEY_PATH");

            if (string.IsNullOrWhiteSpace(privateKeyPath))
            {
                throw new InvalidOperationException("JWT private key path is not configured. Set JwtKeys:PrivateKeyPath or JWT_PRIVATE_KEY_PATH.");
            }

            var creds = new SigningCredentials(JwtKeyHelper.GetPrivateKey(privateKeyPath), SecurityAlgorithms.RsaSha256);

            var tokenDescriptor = new JwtSecurityToken(
                issuer: config.GetValue<string>("AppSettings:Issuer"),
                audience: config.GetValue<string>("AppSettings:Audience"),
                claims: claims,
                expires: DateTime.UtcNow.AddHours(1),
                signingCredentials: creds
                );

            return new JwtSecurityTokenHandler().WriteToken(tokenDescriptor);
        }

        private string GenerateRefreshToken()
        {
            var randomNumber = new byte[32];
            using var rng = RandomNumberGenerator.Create();

            rng.GetBytes(randomNumber);
            return Convert.ToBase64String(randomNumber);
        }

        private async Task<string> GenerateAndSaveRefreshTokenAsync(User user)
        {

            var refreshToken = GenerateRefreshToken();
            user.RefreshToken = refreshToken;
            user.RefreshTokenExpiryTime = DateTime.UtcNow.AddDays(7);
            await dbContext.SaveChangesAsync();

            return refreshToken;
        }
        private async Task<User?> ValidateRefreshTokenAsync(long userId, string refreshToken)
        {
            var user = await dbContext.Users.FindAsync(userId);

            // refresh token is not valid
            if(user == null || user.RefreshToken != refreshToken || user.RefreshTokenExpiryTime <= DateTime.UtcNow)
            {
                return null;
            }

            return user;
        }

        public async Task<TokenResponseDTO?> RefreshTokensAsync(RefreshTokenRequestDTO request)
        {
           var user = await ValidateRefreshTokenAsync
                (request.UserId, request.RefreshToken);

            if (user == null)
                return null;

            return await CreateTokenResponse(user);
        }

       
        // method used for rollback in case Java service throws some expections 
        public async Task DeleteUserAsync(long userId)
        {
            var user = await dbContext.Users.FindAsync(userId);
            if (user != null)
            {
                dbContext.Users.Remove(user);
                await dbContext.SaveChangesAsync();
            }
        }
    }
}
