import { Link, useNavigate } from "react-router-dom";
import { clearTokens, getTokens } from "../api/http";

export default function Nav() {
  const nav = useNavigate();
  const { accessToken } = getTokens();

  const onLogout = () => {
    clearTokens();
    nav("/login");
  };

  return (
    <div style={{ display: "flex", gap: 12, padding: 12, borderBottom: "1px solid #eee" }}>
      <Link to="/">Home</Link>
      <Link to="/register">Register</Link>
      <Link to="/login">Login</Link>
      <Link to="/dashboard">Dashboard</Link>
      <div style={{ marginLeft: "auto" }}>
        {accessToken ? (
          <button onClick={onLogout}>Logout</button>
        ) : (
          <span style={{ opacity: 0.7 }}>Not logged in</span>
        )}
      </div>
    </div>
  );
}
