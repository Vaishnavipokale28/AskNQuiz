import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./StudentRegister.css";

function StudentRegister() {
  const navigate = useNavigate();

  const [student, setStudent] = useState({
    studentName: "",
    email: "",
    password: "",
    confirmPassword: "",
    admissionDate: "",
    courseId: ""
  });

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (student.password !== student.confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    try {
      await axios.post(
        "http://localhost:8080/students/resgister",
        {
          studentName: student.studentName,
          email: student.email,
          password: student.password,
          admissionDate: student.admissionDate,
          courseId: Number(student.courseId)
        }
      );

      alert("Student Registered Successfully");
      navigate("/login");

    } catch (error) {
      console.error(error.response?.data);
      alert(error.response?.data?.message || "Registration Failed");
    }
  };

  return (
    <div className="register-container">
      <form className="register-form" onSubmit={handleSubmit}>
        <h2>Student Registration</h2>

        <div className="form-group">
          <label>Student Name</label>
          <input
            type="text"
            name="studentName"
            value={student.studentName}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Email</label>
          <input
            type="email"
            name="email"
            value={student.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={student.password}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Confirm Password</label>
          <input
            type="password"
            name="confirmPassword"
            value={student.confirmPassword}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Admission Date</label>
          <input
            type="date"
            name="admissionDate"
            value={student.admissionDate}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Course ID</label>
          <input
            type="number"
            name="courseId"
            value={student.courseId}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit" className="btn-register">
          Register
        </button>
      </form>
    </div>
  );
}

export default StudentRegister;
