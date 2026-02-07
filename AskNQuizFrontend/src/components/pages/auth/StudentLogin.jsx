import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./StudentLogin.css";

function StudentLogin() {
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
      "http://localhost:8080/students/login",
      loginData
    );

    
    const student = {
      studentId: res.data.studentId,
      studentName: res.data.studentName,
      email: res.data.email
    };

    localStorage.setItem("student", JSON.stringify(student));

    alert("Login successful");
    navigate("/student");

  } catch (err) {
    alert(err.response?.data?.message || "Login failed");
  }
};


  return (
    <div className="login-page">
      <div className="login-card">
        <h2>Student Login</h2>

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

export default StudentLogin;
