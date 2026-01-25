using UserAuthService.DTOs;
using UserAuthService.Entities;

namespace UserAuthService.Services
{
    public interface IAuthService
    {
        Task<User?> RegisterAsync(UserDTO userDTO);
        Task<TokenResponseDTO?> LoginAsync(UserDTO userDTO);

        Task<TokenResponseDTO?> RefreshTokensAsync(RefreshTokenRequestDTO request);
    }
}
