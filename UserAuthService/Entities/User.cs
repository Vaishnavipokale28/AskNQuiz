namespace UserAuthService.Entities
{
    public class User
    {
        //properties
        public long UserId { get; set; }
        public string Email { get; set; } = string.Empty;
        public string HashedPassword { get; set; } = string.Empty;
        public UserRole Role {  get; set; } 

        //We use refresh tokens to balance security and user experience by keeping access tokens short-lived while allowing seamless re-authentication without forcing the user to log in again.
        public string? RefreshToken { get; set; }

        public DateTime? RefreshTokenExpiryTime { get; set; }
    }
}
