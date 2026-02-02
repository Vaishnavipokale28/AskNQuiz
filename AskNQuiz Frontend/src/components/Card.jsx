export default function Card({ title, children }) {
  return (
    <div style={{
      border: "1px solid #eee",
      borderRadius: 12,
      padding: 16,
      marginBottom: 12,
      background: "white"
    }}>
      {title ? <h3 style={{ marginTop: 0 }}>{title}</h3> : null}
      {children}
    </div>
  );
}
