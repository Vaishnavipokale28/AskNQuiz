import "./Navbar.css";
import { useNavigate } from "react-router-dom";

function Navbar({ title, user }) {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div className="navbar">
      <h2>{title}</h2>

      <div className="navbar-right">
        <span>{user?.email}</span>
        <button onClick={logout}>Logout</button>
      </div>
    </div>
  );
}

export default Navbar;
