using System.Text.Json.Serialization; // Required for mapping names

namespace UserAuthService.DTOs
{
    public class StudentDTO
    {
        // Java: private String studentName;
        [JsonPropertyName("studentName")]
        public string StudentName { get; set; } = string.Empty;

        // Java: private String email;
        [JsonPropertyName("email")]
        public string Email { get; set; } = string.Empty;

        // Java: private String password;
        [JsonPropertyName("password")]
        public string Password { get; set; } = string.Empty;

        

        
    }
}