import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./TeacherLogin.css";

function TeacherLogin() {
  const navigate = useNavigate();

  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    setLoginData({ ...loginData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post(
        "http://localhost:8080/teachers/login",
        loginData
      );

      // ✅ store teacher info
      localStorage.setItem("teacher", JSON.stringify(res.data));

      alert("Teacher login successful");
      navigate("/teacher/dashboard");

    } catch (err) {
      alert(err.response?.data?.msg || "Login failed");
    }
  };

  return (
    <div className="login-page">
      <div className="login-card">
        <h2>Teacher Login</h2>

        <form onSubmit={handleSubmit}>
          <label>Email</label>
          <input
            type="email"
            name="email"
            value={loginData.email}
            onChange={handleChange}
            required
          />

          <label>Password</label>
          <input
            type="password"
            name="password"
            value={loginData.password}
            onChange={handleChange}
            required
          />

          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
}

export default TeacherLogin;
