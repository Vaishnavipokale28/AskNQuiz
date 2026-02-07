import { useState } from "react";
import axios from "axios";
import "./ReplyDoubt.css";

function ReplyDoubt() {
  const [doubtId, setDoubtId] = useState("");
  const [reply, setReply] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!doubtId || !reply) {
      alert("Please enter doubt ID and reply");
      return;
    }

    try {
      setLoading(true);

      await axios.put(
        `http://localhost:8080/doubts/reply/${doubtId}?reply=${reply}`
      );

      alert("Reply sent successfully");
      setDoubtId("");
      setReply("");
    } catch (err) {
      alert("Failed to send reply");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="reply-page">
      <div className="reply-card">
        <h2>Reply to Student Doubt</h2>

        <form onSubmit={handleSubmit}>
          <input
            type="number"
            placeholder="Doubt ID"
            value={doubtId}
            onChange={(e) => setDoubtId(e.target.value)}
          />

          <textarea
            rows="4"
            placeholder="Write your reply here..."
            value={reply}
            onChange={(e) => setReply(e.target.value)}
          />

          <button disabled={loading}>
            {loading ? "Sending..." : "Send Reply"}
          </button>
        </form>
      </div>
    </div>
  );
}

export default ReplyDoubt;
