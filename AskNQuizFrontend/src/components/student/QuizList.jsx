// import { useEffect, useState } from "react";
// import axios from "axios";
// import { useNavigate } from "react-router-dom";
// import "./QuizList.css";

// const studentId = 1; // TEMP (later from login)

// function QuizList() {
//   const [quizzes, setQuizzes] = useState([]);
//   const navigate = useNavigate();

//   useEffect(() => {
//     axios
//       .get("http://localhost:8080/quiz/available")
//       .then((res) => setQuizzes(res.data))
//       .catch((err) => alert(err.response?.data?.message));
//   }, []);

//   const startQuiz = async (quizId) => {
//     try {
//       const res = await axios.post(
//         "http://localhost:8080/attempts/start",
//         {
//           quizId,
//           studentId,
//         }
//       );

//       //  store attemptId
//       localStorage.setItem("attemptId", res.data.attemptId);

//       navigate(`/student/attempt/${quizId}/questions`);
//     } catch (err) {
//       alert(err.response?.data?.message || "Failed to start quiz");
//     }
//   };

//   return (
//     <div className="quiz-container">
//       <h2>Available Quizzes</h2>

//       <div className="quiz-card-container">
//         {quizzes.map((quiz) => (
//           <div className="quiz-card" key={quiz.quizId}>
//             <h3>{quiz.title}</h3>
//             <p><strong>Status:</strong> {quiz.status}</p>
//             <button onClick={() => startQuiz(quiz.quizId)}>
//               Start Quiz
//             </button>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// }

// export default QuizList;


import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./QuizList.css";

function QuizList() {
  const [quizzes, setQuizzes] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:8080/quiz/available")
      .then((res) => setQuizzes(res.data))
      .catch((err) => alert(err.response?.data?.message));
  }, []);

  
  const startQuiz = (quizId) => {
    navigate(`/student/attempt/${quizId}`);
  };

  return (
    <div className="quiz-container">
      <h2>Available Quizzes</h2>

      <div className="quiz-card-container">
        {quizzes.map((quiz) => (
          <div className="quiz-card" key={quiz.quizId}>
            <h3>{quiz.title}</h3>
            <p><strong>Status:</strong> {quiz.status}</p>

            <button onClick={() => startQuiz(quiz.quizId)}>
              Start Quiz
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default QuizList;

