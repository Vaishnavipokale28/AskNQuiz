import { useState } from "react";
import { askDoubt } from "../../../api/doubtApi";
import "./AskDoubt.css";

function AskDoubt() {
  const student = JSON.parse(localStorage.getItem("student"));

  const [form, setForm] = useState({
    title: "",
    description: "",
    teacherId: ""
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();

    await askDoubt({
      ...form,
      studentId: student.studentId
    });

    alert("Doubt submitted successfully");
    setForm({ title: "", description: "", teacherId: "" });
  };

  return (
    <div className="doubt-page">
      <div className="doubt-card">
        <h2>Ask a Doubt</h2>

        <form onSubmit={handleSubmit}>
          <input
            name="title"
            placeholder="Doubt Title"
            value={form.title}
            onChange={handleChange}
            required
          />

          <textarea
            name="description"
            placeholder="Describe your doubt"
            rows="4"
            value={form.description}
            onChange={handleChange}
            required
          />

          <input
            name="teacherId"
            placeholder="Teacher ID"
            value={form.teacherId}
            onChange={handleChange}
            required
          />

          <button>Submit Doubt</button>
        </form>
      </div>
    </div>
  );
}

export default AskDoubt;
