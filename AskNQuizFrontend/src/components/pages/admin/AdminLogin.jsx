import "./AdminLogin.css";

function AdminLogin() {
  return (
    <div className="login-page">
      <div className="login-card">
        <h2>Admin Login</h2>

        <input type="email" placeholder="Email" />
        <input type="password" placeholder="Password" />
        <button>Login</button>
      </div>
    </div>
  );
}

export default AdminLogin;
