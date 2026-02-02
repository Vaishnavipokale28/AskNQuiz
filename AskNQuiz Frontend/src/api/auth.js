import { authApi, setTokens } from "./http";

export async function registerUser(payload) {
  // payload matches .NET UserDTO: {email,password,name,role,admissionDate}
  const res = await authApi.post("/Auth/register", payload, {
    headers: { "Content-Type": "application/json" },
  });
  return res.data;
}

export async function loginUser(payload) {
  const res = await authApi.post("/Auth/login", payload, {
    headers: { "Content-Type": "application/json" },
  });
  // .NET returns TokenResponseDTO: { accessToken, refreshToken }
  if (res.data?.accessToken && res.data?.refreshToken) {
    setTokens(res.data.accessToken, res.data.refreshToken);
  }
  return res.data;
}
