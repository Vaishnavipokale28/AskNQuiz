import api from "./axios";

// 1. Get released quizzes (student)
export const getReleasedQuizzes = () =>
  api.get("/quiz/available");

// 2. Start quiz (creates attempt)
export const startQuiz = (quizId, studentId) =>
  api.post("/attempts/start", {
    quizId,
    studentId,
  });

// 3. Get questions of quiz
export const getQuestionsByQuiz = (quizId) =>
  api.get(`/questions/quiz/${quizId}`);

// 4. Submit quiz
export const submitQuiz = (attemptId, answers) =>
  api.post("/attempts/submit", {
    attemptId,
    answers,
  });
