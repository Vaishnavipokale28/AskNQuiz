import { useState } from "react";
import axios from "axios";
import "./AddTeacher.css";

function AddTeacher() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    subject: "",
    courseId: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post(
        "http://localhost:8080/admin/add-teacher",
        {
          name: form.name,
          email: form.email,
          password: form.password,   // 🔴 VERY IMPORTANT
          subject: form.subject,
          courseId: Number(form.courseId)
        }
      );

      alert(res.data.message || "Teacher added successfully");

      setForm({
        name: "",
        email: "",
        password: "",
        subject: "",
        courseId: ""
      });
    } catch (err) {
      alert(err.response?.data?.message || "Failed to add teacher");
    }
  };

  return (
    <div className="add-teacher-page">
      <div className="add-teacher-card">
        <h2>Add Teacher</h2>

        <form onSubmit={handleSubmit}>
          <input
            name="name"
            placeholder="Teacher Name"
            value={form.name}
            onChange={handleChange}
            required
          />

          <input
            name="email"
            type="email"
            placeholder="Email"
            value={form.email}
            onChange={handleChange}
            required
          />

          <input
            name="password"
            type="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
          />

          <input
            name="subject"
            placeholder="Subject"
            value={form.subject}
            onChange={handleChange}
            required
          />

          <input
            name="courseId"
            type="number"
            placeholder="Course ID"
            value={form.courseId}
            onChange={handleChange}
            required
          />

          <button type="submit">Add Teacher</button>
        </form>
      </div>
    </div>
  );
}

export default AddTeacher;
