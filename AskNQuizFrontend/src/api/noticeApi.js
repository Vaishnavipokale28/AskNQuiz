import axios from "axios";

const BASE_URL = "http://localhost:8080/notices";

export const postNotice = (data) =>
  axios.post(`${BASE_URL}/post`, data);

export const fetchAllNotices = () =>
  axios.get(`${BASE_URL}/all`);

export const fetchNoticesByTeacher = (teacherId) =>
  axios.get(`${BASE_URL}/teacher/${teacherId}`);
