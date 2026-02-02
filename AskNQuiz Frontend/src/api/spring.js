import { springApi } from "./http";

export async function listStudents() {
  const res = await springApi.get("/students/list");
  return res.data;
}

export async function listAllQuizzes() {
  const res = await springApi.get("/quiz/all");
  return res.data;
}

export async function listAvailableQuizzes() {
  const res = await springApi.get("/quiz/available");
  return res.data;
}

export async function listNotices() {
  const res = await springApi.get("/notices/all");
  return res.data;
}
