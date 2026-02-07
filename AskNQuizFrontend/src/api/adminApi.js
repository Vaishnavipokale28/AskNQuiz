import axios from "axios";

const BASE_URL = "http://localhost:8080/admin";

export const adminLogin = (data) =>
  axios.post(`${BASE_URL}/login`, data);

export const addTeacher = (data) =>
  axios.post(`${BASE_URL}/add-teacher`, data);

export const getAllStudents = () =>
  axios.get(`${BASE_URL}/students`);

export const getAllTeachers = () =>
  axios.get(`${BASE_URL}/teachers`);
