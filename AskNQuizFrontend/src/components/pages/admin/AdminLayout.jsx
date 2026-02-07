import { Link, Outlet } from "react-router-dom";
import "./AdminLayout.css";

function AdminLayout() {
  return (
    <div className="admin-layout">
      <aside className="sidebar">
        <h3>Admin Panel</h3>
        <Link to="/admin/dashboard">Dashboard</Link>
        <Link to="/admin/add-teacher">Add Teacher</Link>
        <Link to="/admin/students">Students</Link>
        <Link to="/admin/teachers">Teachers</Link>
      </aside>

      <main className="admin-content">
        <Outlet />
      </main>
    </div>
  );
}

export default AdminLayout;
