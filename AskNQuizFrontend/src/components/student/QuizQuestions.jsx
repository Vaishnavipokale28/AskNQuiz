import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function QuizQuestions() {
  const { quizId } = useParams();
  const navigate = useNavigate();

  const attemptId = localStorage.getItem("attemptId");

  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});

  useEffect(() => {
    if (!attemptId) {
      alert("Quiz attempt not started");
      navigate("/student/quizzes");
      return;
    }

    axios
      .get(`http://localhost:8080/questions/quiz/${quizId}`)
      .then((res) => setQuestions(res.data))
      .catch((err) =>
        alert(err.response?.data?.message || "Failed to load questions")
      );
  }, [quizId, attemptId, navigate]);

  const handleChange = (questionId, optionId) => {
    setAnswers((prev) => ({
      ...prev,
      [questionId]: optionId
    }));
  };

  const submitQuiz = async () => {
    if (Object.keys(answers).length !== questions.length) {
      alert("Please answer all questions");
      return;
    }

    const payload = {
      attemptId: Number(attemptId),
      answers
    };

    try {
      const res = await axios.post("http://localhost:8080/attempts/submit", payload);

      // alert(`Quiz submitted! Score: ${res.data.score}`);
      // localStorage.removeItem("attemptId");
      // navigate("/student");

      alert(res.data.msg || "Quiz submitted successfully!");
      navigate(`/student/result/${attemptId}`);


    } catch (err) {
      alert(err.response?.data?.msg || err.response?.data?.message || "Submission failed");
    }

    // try {
    //   const res = await axios.post(
    //     "http://localhost:8080/attempts/submit",
    //     payload
    //   );

    //   alert(`Quiz submitted! Score: ${res.data.score}`);
    //   localStorage.removeItem("attemptId");
    //   navigate("/student");
    // } catch (err) {
    //   alert(err.response?.data?.message || "Submission failed");
    // }
  };

  return (
    <div style={{ padding: "30px" }}>
      <h2>Quiz Questions</h2>

      {questions.map((q, index) => (
        <div key={q.questionId} style={{ marginBottom: "20px" }}>
          <h4>
            {index + 1}. {q.questionText}
          </h4>

          {q.options.map((opt) => (
            <div key={opt.optionId}>
              <label>
                <input
                  type="radio"
                  name={`q-${q.questionId}`}
                  onChange={() =>
                    handleChange(q.questionId, opt.optionId)
                  }
                />
                {opt.optionText}
              </label>
            </div>
          ))}
        </div>
      ))}

      {questions.length > 0 && (
        <button onClick={submitQuiz}>Submit Quiz</button>
      )}
    </div>
  );
}

export default QuizQuestions;
