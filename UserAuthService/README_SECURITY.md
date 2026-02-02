# Secrets & configuration (GitHub-safe)

This project previously contained sensitive values in `appsettings.json` (JWT token/secret values and DB credentials).
They have been removed and replaced with placeholders.

## What you must configure locally

### 1) Database connection string
Set **one** of the following:

- `ConnectionStrings:DefaultConnection` in `appsettings.Development.json` (local only), or
- Environment variable:

`ConnectionStrings__DefaultConnection`

Example (Windows PowerShell):

```
$env:ConnectionStrings__DefaultConnection="Server=localhost;Database=auth_db;User=root;Password=YOUR_PASSWORD;"
```

### 2) JWT RSA key paths
Set the RSA key file paths (private key only on this Auth service):

- `JwtKeys:PrivateKeyPath` or env `JWT_PRIVATE_KEY_PATH`
- `JwtKeys:PublicKeyPath`  or env `JWT_PUBLIC_KEY_PATH`

Example:

```
$env:JWT_PRIVATE_KEY_PATH="C:\secret\Keys\private.key"
$env:JWT_PUBLIC_KEY_PATH="C:\secret\Keys\public.key"
```

> Do **not** commit the key files to Git. Keep them outside the repo (or in `Keys/` which is ignored).

### 3) CORS (frontend)
Update `Cors:AllowedOrigins` in `appsettings.json` as needed (default includes `http://localhost:5173`).

## Files ignored by Git
- `bin/`, `obj/`, `.vs/`
- `Keys/` (recommended if you keep local keys inside the project folder)
- `appsettings.*.local.json`
