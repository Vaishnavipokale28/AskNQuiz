import { useEffect, useState } from "react";
import Card from "../components/Card";
import { listAllQuizzes, listAvailableQuizzes, listNotices, listStudents } from "../api/spring";
import { getTokens } from "../api/http";

function Json({ value }) {
  return (
    <pre style={{
      background: "#0b1020",
      color: "#d7e0ff",
      padding: 12,
      borderRadius: 10,
      overflowX: "auto",
      fontSize: 12
    }}>{JSON.stringify(value, null, 2)}</pre>
  );
}

export default function Dashboard() {
  const [students, setStudents] = useState(null);
  const [quizzes, setQuizzes] = useState(null);
  const [available, setAvailable] = useState(null);
  const [notices, setNotices] = useState(null);
  const [err, setErr] = useState("");

  const { accessToken } = getTokens();

  useEffect(() => {
    (async () => {
      setErr("");
      try {
        const [s, q, a, n] = await Promise.all([
          listStudents(),
          listAllQuizzes(),
          listAvailableQuizzes(),
          listNotices(),
        ]);
        setStudents(s);
        setQuizzes(q);
        setAvailable(a);
        setNotices(n);
      } catch (e) {
        const msg = e?.response?.data || e?.message || "Unknown error";
        setErr(String(msg));
      }
    })();
  }, []);

  return (
    <div style={{ maxWidth: 980, margin: "24px auto", padding: "0 12px" }}>
      <Card title="Dashboard (reads Spring APIs)">
        <p style={{ marginTop: 0 }}>
          Token present: <b>{accessToken ? "YES" : "NO"}</b>{" "}
          <span style={{ opacity: 0.7 }}>(token is attached to Spring requests)</span>
        </p>
        {err ? <p style={{ color: "crimson" }}>❌ Error: {err}</p> : null}
      </Card>

      <Card title="Students — GET /students/list">{students ? <Json value={students} /> : <p>Loading...</p>}</Card>
      <Card title="All Quizzes — GET /quiz/all">{quizzes ? <Json value={quizzes} /> : <p>Loading...</p>}</Card>
      <Card title="Available Quizzes — GET /quiz/available">{available ? <Json value={available} /> : <p>Loading...</p>}</Card>
      <Card title="Notices — GET /notices/all">{notices ? <Json value={notices} /> : <p>Loading...</p>}</Card>
    </div>
  );
}
