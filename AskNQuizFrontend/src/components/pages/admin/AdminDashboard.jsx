import "../../common/Dashboard.css";

import Navbar from "../../common/Navbar";
import { useNavigate } from "react-router-dom";

function AdminDashboard() {
  const navigate = useNavigate();
  const admin = JSON.parse(localStorage.getItem("admin"));

  if (!admin) return <h2>Please login</h2>;

  return (
    <>
      <Navbar title="Admin Dashboard" user={admin} />

      <div className="dashboard-container">
        <h2>Admin Panel</h2>

        <div className="card-grid">
          <div className="card">
            <h3>Add Teacher</h3>
            <button onClick={() => navigate("/admin/add-teacher")}>
              Add Teacher
            </button>
          </div>

          <div className="card">
            <h3>All Students</h3>
            <button onClick={() => navigate("/admin/students")}>
              View Students
            </button>
          </div>

          <div className="card">
            <h3>All Teachers</h3>
            <button onClick={() => navigate("/admin/teachers")}>
              View Teachers
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default AdminDashboard;
