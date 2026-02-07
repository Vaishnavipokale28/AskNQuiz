import { useEffect, useState } from "react";
import { fetchAllNotices } from "../../../api/noticeApi";
import "./NoticeList.css";

function NoticeList() {
  const [notices, setNotices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const loadNotices = async () => {
      try {
        const response = await fetchAllNotices();
        setNotices(response.data);
      } catch (err) {
        console.error("Error fetching notices:", err);
        setError("Failed to load notices. Please try again later.");
      } finally {
        setLoading(false);
      }
    };

    loadNotices();
  }, []);

  if (loading) {
    return (
      <div className="notice-list-page">
        <h2>Latest Notices</h2>
        <p>Loading notices...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="notice-list-page">
        <h2>Latest Notices</h2>
        <p className="error-text">{error}</p>
      </div>
    );
  }

  return (
    <div className="notice-list-page">
      <h2>Latest Notices</h2>

      {notices.length === 0 ? (
        <p>No notices available.</p>
      ) : (
        <div className="notice-list">
          {notices.map((notice, index) => (
            <div key={index} className="notice-box">
              <h3>{notice.title}</h3>

              <p className="notice-message">
                {notice.message}
              </p>

              <div className="notice-footer">
                <span>
                  Posted by:{" "}
                  <strong>
                    {notice.teacherName || "Unknown"}
                  </strong>
                </span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default NoticeList;
