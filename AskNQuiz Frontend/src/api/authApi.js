import axios from "axios";

const authApi = axios.create({
  baseURL: import.meta.env.VITE_AUTH_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const registerUser = (data) => {
  return authApi.post("/Auth/register", data);
};

export const loginUser = (data) => {
  return authApi.post("/Auth/login", data);
};
