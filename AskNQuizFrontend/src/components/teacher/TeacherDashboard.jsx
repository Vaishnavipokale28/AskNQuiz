import "../common/Dashboard.css";
import Navbar from "../common/Navbar";
import { useNavigate } from "react-router-dom";

function TeacherDashboard() {
  const navigate = useNavigate();
  const teacher = JSON.parse(localStorage.getItem("teacher"));

  if (!teacher) return <h2>Please login</h2>;

  return (
    <>
      <Navbar title="Teacher Dashboard" user={teacher} />

      <div className="dashboard-container">
        <h2>
          Welcome, {teacher.teacherName || teacher.name || "Teacher"}
        </h2>

        <div className="card-grid">
          <div className="card">
            <h3>Create Quiz</h3>
            <p>Create new quiz</p>
            <button onClick={() => navigate("/teacher/create-quiz")}>
              Create Quiz
            </button>
          </div>

          <div className="card">
            <h3>Add Questions</h3>
            <p>Add questions to quiz</p>
            <button onClick={() => navigate("/teacher/add-questions")}>
              Add Questions
            </button>
          </div>

          {/* ✅ NEW CARD */}
          <div className="card">
            <h3>Release Quiz</h3>
            <p>Make quiz visible to students</p>
            <button onClick={() => navigate("/teacher/release-quiz")}>
              Release Quiz
            </button>
          </div>

          <div className="card">
            <h3>Reply Doubts</h3>
            <p>Answer student doubts</p>
            <button onClick={() => navigate("/teacher/reply-doubt")}>
              Reply
            </button>
          </div>

          <div className="card">
            <h3>Post Notice</h3>
            <p>Send announcement</p>
            <button onClick={() => navigate("/teacher/post-notice")}>
              Post Notice
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default TeacherDashboard;
