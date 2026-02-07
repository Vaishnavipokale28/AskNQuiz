import axios from "axios";

const BASE_URL = "http://localhost:8080/doubts";

export const askDoubt = (data) =>
  axios.post(`${BASE_URL}/ask`, data);

export const getStudentDoubts = (studentId) =>
  axios.get(`${BASE_URL}/student/${studentId}`);

export const getTeacherDoubts = (teacherId) =>
  axios.get(`${BASE_URL}/teacher/${teacherId}`);

export const replyToDoubt = (doubtId, reply) =>
  axios.put(`${BASE_URL}/reply/${doubtId}?reply=${reply}`);
