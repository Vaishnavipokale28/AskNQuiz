import { useState } from "react";
import Card from "../components/Card";
import { registerUser } from "../api/authApi"; // FIXED IMPORT

export default function Register() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    role: "Student",
    admissionDate: new Date().toISOString().slice(0, 10) + "T00:00:00",
  });

  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState("");

  const onChange = (e) =>
    setForm((f) => ({ ...f, [e.target.name]: e.target.value }));

  const onSubmit = async (e) => {
    e.preventDefault();
    setMsg("");
    setLoading(true);

    try {
      const res = await registerUser(form); //  CALLS .NET (7122)
      setMsg(
        " Registered in .NET and Student created in Spring. UserId = " +
          (res?.data?.userId ?? "(check response)")
      );
    } catch (err) {
      const txt =
        err?.response?.data ||
        err?.message ||
        "Unknown error";
      setMsg(" Register failed: " + txt);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 520, margin: "24px auto", padding: "0 12px" }}>
      <Card title="Register (Frontend → .NET → Spring)">
        <form onSubmit={onSubmit} style={{ display: "grid", gap: 10 }}>
          <label>
            Name
            <input
              name="name"
              value={form.name}
              onChange={onChange}
              placeholder="Your name"
              required
            />
          </label>

          <label>
            Email
            <input
              name="email"
              value={form.email}
              onChange={onChange}
              placeholder="abc@gmail.com"
              required
            />
          </label>

          <label>
            Password
            <input
              name="password"
              type="password"
              value={form.password}
              onChange={onChange}
              placeholder="Test@123"
              required
            />
          </label>

          <label>
            Role
            <select name="role" value={form.role} onChange={onChange}>
              <option value="Student">Student</option>
              <option value="Teacher">Teacher</option>
              <option value="Admin">Admin</option>
            </select>
          </label>

          <label>
            Admission Date
            <input
              name="admissionDate"
              value={form.admissionDate}
              onChange={onChange}
            />
            <small style={{ opacity: 0.7 }}>
              Format: YYYY-MM-DDT00:00:00
            </small>
          </label>

          <button disabled={loading}>
            {loading ? "Registering..." : "Register"}
          </button>
        </form>

        {msg && <p style={{ marginTop: 12 }}>{msg}</p>}
      </Card>
    </div>
  );
}
