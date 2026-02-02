namespace UserAuthService.Helpers
{
    using Microsoft.IdentityModel.Tokens;
    using System.Security.Cryptography;

    
    
        /// <summary>
        /// Helper class responsible for loading RSA keys used for JWT signing and validation.
        /// 
        /// This enables asymmetric-key JWT authentication:
        /// - AuthService signs tokens using the PRIVATE key
        /// - Other microservices verify tokens using the PUBLIC key
        /// 
        /// This approach is language-agnostic and secure for microservice architectures.
        /// </summary>
        public static class JwtKeyHelper
        {
            /// <summary>
            /// Loads the RSA PRIVATE key from file system and returns it as a SecurityKey.
            /// 
            /// This private key MUST only exist in the AuthService.
            /// It is used to SIGN JWT tokens.
            /// 
            /// ⚠️ Never share this key with other microservices.
            /// </summary>
            public static RsaSecurityKey GetPrivateKey(string privateKeyPath)
            {
                // Create an RSA instance (cryptographic provider)
                var rsa = RSA.Create();

                // Read the private key PEM file and import it into RSA
                // This key is used to sign JWT tokens
                rsa.ImportFromPem(File.ReadAllText(privateKeyPath));

                // Wrap RSA key in RsaSecurityKey so it can be used by JWT signing credentials
                return new RsaSecurityKey(rsa);
            }

            /// <summary>
            /// Loads the RSA PUBLIC key from file system and returns it as a SecurityKey.
            /// 
            /// This public key can be safely shared with other microservices
            /// (Java, Go, Node, etc.) so they can VERIFY JWT tokens.
            /// 
            /// Public key cannot be used to sign tokens (only to verify).
            /// </summary>
            public static RsaSecurityKey GetPublicKey(string publicKeyPath)
            {
                // Create an RSA instance (cryptographic provider)
                var rsa = RSA.Create();

                // Read the public key PEM file and import it into RSA
                // This key is used ONLY to validate JWT signatures
                rsa.ImportFromPem(File.ReadAllText(publicKeyPath));

                // Wrap RSA key in RsaSecurityKey so it can be used by JWT validation
                return new RsaSecurityKey(rsa);
            }
        }
    }


