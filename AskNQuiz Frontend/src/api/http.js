import axios from "axios";

const authBaseURL = import.meta.env.VITE_AUTH_BASE_URL;
const springBaseURL = import.meta.env.VITE_SPRING_BASE_URL;

// localStorage keys
const ACCESS = "accessToken";
const REFRESH = "refreshToken";

export function getTokens() {
  return {
    accessToken: localStorage.getItem(ACCESS) || "",
    refreshToken: localStorage.getItem(REFRESH) || "",
  };
}

export function setTokens(accessToken, refreshToken) {
  if (accessToken) localStorage.setItem(ACCESS, accessToken);
  if (refreshToken) localStorage.setItem(REFRESH, refreshToken);
}

export function clearTokens() {
  localStorage.removeItem(ACCESS);
  localStorage.removeItem(REFRESH);
}

export const authApi = axios.create({
  baseURL: authBaseURL,
});

export const springApi = axios.create({
  baseURL: springBaseURL,
});

// Attach bearer token (for future when Spring validates JWT)
springApi.interceptors.request.use((config) => {
  const { accessToken } = getTokens();
  if (accessToken) {
    config.headers = config.headers || {};
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});
