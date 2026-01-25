namespace UserAuthService.DTOs
{
    public class RefreshTokenRequestDTO
    {
        public long UserId { get; set; }
        public required string RefreshToken { get; set; }
    }
}
