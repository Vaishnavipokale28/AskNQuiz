using System.Text.Json.Serialization;

namespace UserAuthService.DTOs
{
    // Must match Spring StudentDto.java JsonProperty names exactly:
    // Name, Email, Password, AdmissionDate
    public class StudentDTO
    {
        [JsonPropertyName("Name")]
        public string Name { get; set; } = string.Empty;

        [JsonPropertyName("Email")]
        public string Email { get; set; } = string.Empty;

        [JsonPropertyName("Password")]
        public string Password { get; set; } = string.Empty;

        // Spring expects ISO date (yyyy-MM-dd). System.Text.Json handles DateOnly well in .NET 8.
        [JsonPropertyName("AdmissionDate")]
        public DateOnly AdmissionDate { get; set; } = DateOnly.FromDateTime(DateTime.UtcNow);
    }
}
