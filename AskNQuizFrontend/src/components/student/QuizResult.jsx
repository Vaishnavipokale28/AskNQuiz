import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function QuizResult() {
  const { attemptId } = useParams();
  const navigate = useNavigate();

  const [data, setData] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/attempts/result/${attemptId}`)
      .then((res) => {
        setData(res.data);
        // optional cleanup
        localStorage.removeItem("attemptId");
      })
      .catch((err) => {
        alert(err.response?.data?.msg || err.response?.data?.message || "Failed to load result");
        navigate("/student");
      });
  }, [attemptId, navigate]);

  if (!data) return <div style={{ padding: 30 }}>Loading result...</div>;

  return (
    <div style={{ padding: 30 }}>
      <h2>Quiz Result</h2>

      <p><b>Score:</b> {data.score}</p>
      <p><b>Total Questions:</b> {data.totalQuestions}</p>
      <p><b>Correct:</b> {data.correctCount}</p>
      <p><b>Wrong:</b> {data.wrongCount}</p>

      <hr />

      {data.details.map((q, idx) => (
        <div key={q.questionId} style={{ marginBottom: 18 }}>
          <h4>
            {idx + 1}. {q.questionText}
          </h4>

          <p>
            <b>Your Answer:</b> {q.selectedOptionText}
          </p>
          <p>
            <b>Correct Answer:</b> {q.correctOptionText}
          </p>

          <p style={{ fontWeight: "bold" }}>
            {q.correct ? "✅ Correct" : "❌ Wrong"}
          </p>
        </div>
      ))}

      <button onClick={() => navigate("/student")}>Back to Dashboard</button>
    </div>
  );
}

export default QuizResult;
