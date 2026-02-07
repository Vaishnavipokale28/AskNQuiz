import { useState } from "react";
import { postNotice } from "../../../api/noticeApi";
import "./PostNotice.css";

function PostNotice() {
  const [form, setForm] = useState({
    title: "",
    message: "",
    teacherId: ""
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    if (!form.title || !form.message || !form.teacherId) {
      setError("All fields are required");
      return;
    }

    try {
      setLoading(true);
      await postNotice(form);
      setSuccess("Notice posted successfully");
      setForm({ title: "", message: "", teacherId: "" });
    } catch (err) {
      console.error(err);
      setError("Failed to post notice. Check Teacher ID.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="post-notice-page">
      <div className="post-notice-card">
        <h2>Post New Notice</h2>

        {error && <p className="error-msg">{error}</p>}
        {success && <p className="success-msg">{success}</p>}

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="title"
            placeholder="Notice Title"
            value={form.title}
            onChange={handleChange}
          />

          <textarea
            name="message"
            placeholder="Notice Message"
            rows="5"
            value={form.message}
            onChange={handleChange}
          />

          <input
            type="number"
            name="teacherId"
            placeholder="Teacher ID"
            value={form.teacherId}
            onChange={handleChange}
          />

          <button type="submit" disabled={loading}>
            {loading ? "Posting..." : "Post Notice"}
          </button>
        </form>
      </div>
    </div>
  );
}

export default PostNotice;
