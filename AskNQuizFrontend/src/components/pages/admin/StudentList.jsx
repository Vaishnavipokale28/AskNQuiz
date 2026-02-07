import { useEffect, useState } from "react";
import { getAllStudents } from "../../../api/adminApi";
import "./Table.css";



function StudentList() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    getAllStudents().then((res) => setStudents(res.data));
  }, []);

  return (
    <div>
      <h2>All Students</h2>

      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
          </tr>
        </thead>

        <tbody>
          {students.map((s) => (
            <tr key={s.studentId}>
              <td>{s.studentId}</td>
              <td>{s.studentName}</td>
              <td>{s.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default StudentList;
