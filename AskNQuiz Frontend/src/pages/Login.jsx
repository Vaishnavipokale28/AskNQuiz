import { useState } from "react";
import Card from "../components/Card";
import { loginUser } from "../api/auth";

export default function Login() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState("");

  const onChange = (e) => setForm((f) => ({ ...f, [e.target.name]: e.target.value }));

  const onSubmit = async (e) => {
    e.preventDefault();
    setMsg("");
    setLoading(true);
    try {
      const data = await loginUser({ ...form, name: "", role: "Student" });
      if (data?.accessToken) setMsg("✅ Login OK. Token saved to localStorage.");
      else setMsg("⚠️ Login response received but no token found.");
    } catch (err) {
      const txt = err?.response?.data || err?.message || "Unknown error";
      setMsg("❌ Login failed: " + txt);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 520, margin: "24px auto", padding: "0 12px" }}>
      <Card title="Login (.NET returns AccessToken + RefreshToken)">
        <form onSubmit={onSubmit} style={{ display: "grid", gap: 10 }}>
          <label>
            Email
            <input name="email" value={form.email} onChange={onChange} placeholder="abc@gmail.com" style={{ width: "100%" }} />
          </label>
          <label>
            Password
            <input name="password" type="password" value={form.password} onChange={onChange} placeholder="Test@123" style={{ width: "100%" }} />
          </label>

          <button disabled={loading}>{loading ? "Logging in..." : "Login"}</button>
        </form>

        {msg ? <p style={{ marginTop: 12 }}>{msg}</p> : null}
      </Card>
    </div>
  );
}
