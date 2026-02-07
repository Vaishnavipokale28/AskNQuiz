import { useEffect, useState } from "react";
import { getAllTeachers } from "../../../api/adminApi";
import "./Table.css";



function TeacherList() {
  const [teachers, setTeachers] = useState([]);

  useEffect(() => {
    getAllTeachers().then((res) => setTeachers(res.data));
  }, []);

  return (
    <div>
      <h2>All Teachers</h2>

      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Subject</th>
          </tr>
        </thead>

        <tbody>
          {teachers.map((t) => (
            <tr key={t.teacherId}>
              <td>{t.teacherId}</td>
              <td>{t.name}</td>
              <td>{t.email}</td>
              <td>{t.subject}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TeacherList;
