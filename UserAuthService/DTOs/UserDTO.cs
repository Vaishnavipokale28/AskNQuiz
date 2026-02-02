namespace UserAuthService.DTOs
{
    public class UserDTO
    {
        //properties
        public string Email { get; set; } = string.Empty;
        public string Password { get; set; } = string.Empty;

        public string Name { get; set; } = string.Empty;
        public string Role { get; set; }

        public DateTime AdmissionDate { get; set; } = DateTime.Now;
    }
}
