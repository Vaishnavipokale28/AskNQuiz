import api from "./axios";

export const addStudent = (data) =>
  api.post("/students/add", data);

export const deleteStudent = (id) =>
  api.delete(`/students/delete/${id}`);
