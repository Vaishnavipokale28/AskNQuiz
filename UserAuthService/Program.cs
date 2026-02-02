using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using UserAuthService.Data;
using UserAuthService.Helpers;
using UserAuthService.Services;
using UserAuthService.Clients;
using Pomelo.EntityFrameworkCore.MySql.Infrastructure;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// configure DbContext so that ASP.NET Core Dependency Injection (DI) container will manage the lifetime of DbContext, there is one DbContext object per HTTP request

var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");

if (string.IsNullOrWhiteSpace(connectionString) || connectionString.Contains("__DB_USER__") || connectionString.Contains("__DB_PASSWORD__"))
{
    throw new InvalidOperationException("Database connection string is not configured. Set ConnectionStrings:DefaultConnection (or env var ConnectionStrings__DefaultConnection).");
}

builder.Services.AddDbContext<UserAuthDbContext>(options =>
    options.UseMySql(
        connectionString,
        ServerVersion.AutoDetect(connectionString)
    )
);

builder.Services.AddHttpClient();

//AddScoped<IAuthService, AuthService>() registers a service in the DI container so that a new instance of AuthService is created per HTTP request and injected wherever IAuthService is required.
builder.Services.AddScoped<IAuthService, AuthService>();

var publicKeyPath = builder.Configuration["JwtKeys:PublicKeyPath"]
    ?? Environment.GetEnvironmentVariable("JWT_PUBLIC_KEY_PATH");

if (string.IsNullOrWhiteSpace(publicKeyPath))
{
    throw new InvalidOperationException("JWT public key path is not configured. Set JwtKeys:PublicKeyPath or JWT_PUBLIC_KEY_PATH.");
}

var publicKey = JwtKeyHelper.GetPublicKey(publicKeyPath);

// to tell the application to use Jwt token 
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidIssuer = builder.Configuration["AppSettings:Issuer"],

            ValidateAudience = true,
            ValidAudience = builder.Configuration["AppSettings:Audience"],

            ValidateLifetime = true,
            IssuerSigningKey = publicKey,

            ValidateIssuerSigningKey = true,

            // set time difference tolerance to 0, to ensure strict expiration of token, important for microservices
            ClockSkew = TimeSpan.Zero
        };

    });

builder.Services.AddCors(options =>
{
    var allowedOrigins = builder.Configuration.GetSection("Cors:AllowedOrigins").Get<string[]>()
        ?? Array.Empty<string>();

    options.AddPolicy("AllowServices", policy =>
    {
        if (allowedOrigins.Length == 0)
        {
            // Fallback for dev/local testing
            policy.AllowAnyOrigin().AllowAnyHeader().AllowAnyMethod();
        }
        else
        {
            policy.WithOrigins(allowedOrigins)
                  .AllowAnyHeader()
                  .AllowAnyMethod();
        }
    });
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseCors("AllowServices");

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
