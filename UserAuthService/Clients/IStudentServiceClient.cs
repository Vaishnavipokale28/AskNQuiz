using Refit;
using UserAuthService.DTOs;

namespace UserAuthService.Clients
{
    public interface IStudentServiceClient
    {
        
        [Get("/students/list")]
        Task<string> GetAllStudentsAsync([Header("Authorization")] string token);

        // Calls Java's @PostMapping("/students/register")
        [Post("/students/register")]
        Task<string> CreateStudentAsync([Body] StudentDTO studentDto);

    }
}
