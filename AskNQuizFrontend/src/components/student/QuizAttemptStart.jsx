// import { useParams, useNavigate } from "react-router-dom";
// import axios from "axios";
// import { useEffect } from "react";

// function QuizAttemptStart() {
//   const { quizId } = useParams();
//   const navigate = useNavigate();

//   useEffect(() => {
//     const student = JSON.parse(localStorage.getItem("student"));

//     if (!student) {
//       alert("Please login first");
//       navigate("/login");
//       return;
//     }

//     axios
//       .post("http://localhost:8080/attempts/start", {
//         quizId: Number(quizId),
//         studentId: student.studentId
//       })
//       .then((res) => {
//         console.log("Start quiz response:", res.data);

//         localStorage.setItem("attemptId", res.data.attemptId);

//         navigate(`/student/attempt/${quizId}/questions`);
//       })
//       .catch((err) => {
//         alert(err.response?.data?.message || "Failed to start quiz");
//       });
//   }, [quizId, navigate]);

//   return <h3>Starting Quiz...</h3>;
// }

// export default QuizAttemptStart;





import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { useEffect } from "react";

function QuizAttemptStart() {
  const { quizId } = useParams();
  const navigate = useNavigate();

 useEffect(() => {
  const student = JSON.parse(localStorage.getItem("student"));

  if (!student) {
    alert("Please login first");
    navigate("/login");
    return;
  }

  axios
    .post("http://localhost:8080/attempts/start", {
      quizId: Number(quizId),
      studentId: Number(student.studentId),
    })
    .then((res) => {
      // ✅ store everything needed
      localStorage.setItem("attemptId", res.data.attemptId);
      localStorage.setItem("quizQuestions", JSON.stringify(res.data.questions));
      localStorage.setItem("quizTitle", res.data.quizTitle);
      localStorage.setItem("timeLimit", res.data.timeLimit);

      navigate(`/student/attempt/${quizId}/questions`);
    })
    .catch((err) => {
      alert(err.response?.data?.message || "Failed to start quiz");
      navigate("/student/quizzes");
    });
}, [quizId, navigate]);


  return <h3>Starting Quiz...</h3>;
}

export default QuizAttemptStart;

