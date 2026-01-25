using System.Collections.Generic;
using UserAuthService.Entities;
using Microsoft.EntityFrameworkCore;

namespace UserAuthService.Data
{
    public class UserAuthDbContext : DbContext
    {
        public UserAuthDbContext(DbContextOptions<UserAuthDbContext> options)
            : base(options) { }

        public DbSet<User> Users { get; set; }
        //public DbSet<RefreshToken> RefreshTokens { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>()
                .Property(u => u.Role)
                .HasConversion<string>();

            base.OnModelCreating(modelBuilder);
        }


    }
}

    
