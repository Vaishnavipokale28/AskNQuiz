import "../common/Dashboard.css";

import Navbar from "../common/Navbar";
import { useNavigate } from "react-router-dom";

function StudentDashboard() {
  const navigate = useNavigate();
  const student = JSON.parse(localStorage.getItem("student"));

  if (!student) return <h2>Please login</h2>;

  return (
    <>
      <Navbar title="Student Dashboard" user={student} />

      <div className="dashboard-container">
        <h2>Welcome, {student.studentName || "Student"}</h2>

        <div className="card-grid">
          <div className="card">
            <h3>Quizzes</h3>
            <p>View and attempt quizzes</p>
            <button onClick={() => navigate("/student/quizzes")}>
              View Quizzes
            </button>
          </div>

          <div className="card">
            <h3>Notices</h3>
            <p>Latest announcements</p>
            <button onClick={() => navigate("/notices")}>
              View Notices
            </button>
          </div>

          <div className="card">
            <h3>Doubts</h3>
            <p>Ask questions to teachers</p>
            <button onClick={() => navigate("/student/ask-doubt")}>
              Ask Doubt
            </button>
          </div>

          <div className="card">
            <h3>View Reply</h3>
            <p>See teacher replies to your doubts</p>
            <button onClick={() => navigate("/student/my-doubts")}>
              View Reply
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default StudentDashboard;
