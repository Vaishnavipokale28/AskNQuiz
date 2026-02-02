import Card from "../components/Card";

export default function Home() {
  return (
    <div style={{ maxWidth: 900, margin: "24px auto", padding: "0 12px" }}>
      <Card title="AskNQuiz Frontend">
        <p>
          This React app talks to:
          <ul>
            <li><b>.NET Auth</b> for Register/Login (JWT)</li>
            <li><b>Spring</b> for Quiz/Students/Notices APIs (Bearer token attached automatically)</li>
          </ul>
        </p>
        <p style={{ marginBottom: 0 }}>
          Configure base URLs in <code>.env</code> (copy from <code>.env.example</code>).
        </p>
      </Card>
    </div>
  );
}
