using LoggerService.Models;
using LoggerService.Services;
using Microsoft.AspNetCore.Mvc;

namespace LoggerService.Controllers
{
    [ApiController]
    [Route("api/logs")]
    public class LogController : ControllerBase
    {

        private readonly FileLoggerService logger;

        public LogController()
        {
            logger = new FileLoggerService();
        }

        [HttpPost]
        public IActionResult WriteLog([FromBody] LogRequest request)
        {
            logger.Log(request.Service, request.Message);
            return Ok("Log written successfully");
        }

    }
}
