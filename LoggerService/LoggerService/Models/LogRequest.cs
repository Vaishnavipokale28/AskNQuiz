namespace LoggerService.Models
{
    public class LogRequest
    {
        public string Service {  get; set; } = string.Empty;
        public string Message { get; set; } = string.Empty;
    }
}
