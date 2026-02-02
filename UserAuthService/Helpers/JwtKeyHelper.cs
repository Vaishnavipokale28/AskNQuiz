namespace UserAuthService.Helpers
{
    using Microsoft.IdentityModel.Tokens;
    using System.Security.Cryptography;

    /// <summary>
    /// Loads RSA keys used for JWT signing and validation.
    ///
    /// This project ships with a DEV keypair under ./Keys which is copied to output.
    /// In production, replace these keys and store them securely.
    /// </summary>
    public static class JwtKeyHelper
    {
        private static string KeysDir => Path.Combine(AppContext.BaseDirectory, "Keys");

        private static string PrivateKeyPath => Path.Combine(KeysDir, "private.key");
        private static string PublicKeyPath => Path.Combine(KeysDir, "public.key");

        public static RsaSecurityKey GetPrivateKey()
        {
            var rsa = RSA.Create();
            rsa.ImportFromPem(File.ReadAllText(PrivateKeyPath));
            return new RsaSecurityKey(rsa);
        }

        public static RsaSecurityKey GetPublicKey()
        {
            var rsa = RSA.Create();
            rsa.ImportFromPem(File.ReadAllText(PublicKeyPath));
            return new RsaSecurityKey(rsa);
        }
    }
}
