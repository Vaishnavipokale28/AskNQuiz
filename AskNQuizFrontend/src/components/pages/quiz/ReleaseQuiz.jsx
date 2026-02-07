import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Quiz.css";

function ReleaseQuiz() {
  const navigate = useNavigate();
  const teacher = JSON.parse(localStorage.getItem("teacher"));

  const [quizId, setQuizId] = useState(localStorage.getItem("currentQuizId") || "");
  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState("");

  useEffect(() => {
    if (!teacher) navigate("/teacher/login");
  }, [teacher, navigate]);

  const handleRelease = async (e) => {
    e.preventDefault();
    setMsg("");

    const qid = Number(quizId);
    const tid = Number(teacher?.teacherId);

    if (!qid || qid <= 0) {
      setMsg("Please enter a valid Quiz ID.");
      return;
    }
    if (!tid || tid <= 0) {
      setMsg("Teacher ID missing. Please login again.");
      return;
    }

    try {
      setLoading(true);
      const res = await axios.put(
        `http://localhost:8080/quiz/release/${qid}/${tid}`
      );

      setMsg(res.data?.msg || "Quiz released successfully!");
      localStorage.setItem("quizStatus", "RELEASED");
    } catch (err) {
      const backendMsg =
        err?.response?.data?.msg ||
        err?.response?.data?.message ||
        err?.message ||
        "Failed to release quiz";
      setMsg(backendMsg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="quiz-page">
      <div className="quiz-card">
        <h2>Release Quiz</h2>

        <form onSubmit={handleRelease}>
          <input
            type="number"
            placeholder="Enter Quiz ID"
            value={quizId}
            onChange={(e) => setQuizId(e.target.value)}
            required
          />

          <button type="submit" disabled={loading}>
            {loading ? "Releasing..." : "Release Quiz"}
          </button>

          <button
            type="button"
            onClick={() => navigate("/teacher/dashboard")}
            style={{ marginTop: "10px" }}
          >
            Back
          </button>

          {msg && <p style={{ marginTop: 12 }}>{msg}</p>}
        </form>
      </div>
    </div>
  );
}

export default ReleaseQuiz;
