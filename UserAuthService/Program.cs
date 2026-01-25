using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using UserAuthService.Data;
using UserAuthService.Helpers;
using UserAuthService.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// configure DbContext so that ASP.NET Core Dependency Injection (DI) container will manage the lifetime of DbContext, there is one DbContext object per HTTP request
builder.Services.AddDbContext<UserAuthDbContext>(options =>
    options.UseSqlServer(
        builder.Configuration.GetConnectionString("Default")
    ));

//AddScoped<IAuthService, AuthService>() registers a service in the DI container so that a new instance of AuthService is created per HTTP request and injected wherever IAuthService is required.
builder.Services.AddScoped<IAuthService, AuthService>();

var publicKey = JwtKeyHelper.GetPublicKey();

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
    options.AddPolicy("AllowServices", policy =>
        policy.AllowAnyOrigin()
              .AllowAnyHeader()
              .AllowAnyMethod());
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthentication();
app.UseAuthorization();

app.UseCors("AllowServices");


app.MapControllers();

app.Run();
