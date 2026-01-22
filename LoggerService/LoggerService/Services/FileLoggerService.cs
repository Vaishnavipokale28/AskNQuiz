using System.IO;

namespace LoggerService.Services
{
    public class FileLoggerService
    {
        private readonly string logPath = @"D:\DAC\Logs\application.log";

        public void Log(string service, string message)
        {
            Directory.CreateDirectory(Path.GetDirectoryName(logPath)!);

            using var writer = new StreamWriter(logPath, true);
            writer.WriteLine($"[{DateTime.Now}] [{service}] {message}");
        }
    }
}
