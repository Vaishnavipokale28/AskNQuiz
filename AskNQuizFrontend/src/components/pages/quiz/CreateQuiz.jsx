import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Quiz.css";

function CreateQuiz() {
  const navigate = useNavigate();
  const teacher = JSON.parse(localStorage.getItem("teacher"));

  if (!teacher) {
    return <h2>Please login as teacher</h2>;
  }

  const [quiz, setQuiz] = useState({
    title: "",
    subject: "",
    timeLimit: "",
    totalMarks: ""
  });

  const handleChange = (e) => {
    setQuiz({ ...quiz, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/quiz/add", {
        ...quiz,
        teacherId: teacher.teacherId
      });

      // ✅ store quizId so ReleaseQuiz page can auto-fill
      localStorage.setItem("currentQuizId", String(res.data.quizId));
      localStorage.setItem("quizStatus", String(res.data.status || "DRAFT"));

      alert("Quiz created successfully");
      navigate("/teacher/add-questions");
    } catch (err) {
      const msg =
        err?.response?.data?.msg ||
        err?.response?.data?.message ||
        "Failed to create quiz";
      alert(msg);
    }
  };

  return (
    <div className="quiz-page">
      <div className="quiz-card">
        <h2>Create Quiz</h2>

        <form onSubmit={handleSubmit}>
          <input
            name="title"
            placeholder="Quiz Title"
            value={quiz.title}
            onChange={handleChange}
            required
          />
          <input
            name="subject"
            placeholder="Subject"
            value={quiz.subject}
            onChange={handleChange}
            required
          />
          <input
            type="number"
            name="timeLimit"
            placeholder="Time Limit (minutes)"
            value={quiz.timeLimit}
            onChange={handleChange}
            required
          />
          <input
            type="number"
            name="totalMarks"
            placeholder="Total Marks"
            value={quiz.totalMarks}
            onChange={handleChange}
            required
          />

          <button type="submit">Create Quiz</button>
        </form>
      </div>
    </div>
  );
}

export default CreateQuiz;
