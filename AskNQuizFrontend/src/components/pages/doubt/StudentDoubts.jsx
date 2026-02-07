import { useEffect, useState } from "react";
import { getStudentDoubts } from "../../../api/doubtApi";
import "./StudentDoubts.css";

function StudentDoubts() {
  const student = JSON.parse(localStorage.getItem("student"));
  const [doubts, setDoubts] = useState([]);

  useEffect(() => {
    getStudentDoubts(student.studentId)
      .then(res => setDoubts(res.data));
  }, [student.studentId]);

  return (
    <div className="list-page">
			<h2>My Doubts & Replies</h2>

			{doubts.map((d) => (
				<div key={d.doubtId ?? `${d.title}-${d.createdOn}`} className="doubt-box">
					<div className="meta" style={{ marginBottom: 8 }}>
						<span>Doubt ID: {d.doubtId ?? "-"}</span>
						<span>Created: {d.createdOn ? new Date(d.createdOn).toLocaleString() : "-"}</span>
					</div>
          <h3>{d.title}</h3>
					<p>{d.description}</p>

					{d.reply ? (
						<div className="reply-box">
							<h4>Teacher Reply</h4>
							<p>{d.reply}</p>
							{d.repliedOn && (
								<div className="meta">
									<span>Replied On: {new Date(d.repliedOn).toLocaleString()}</span>
								</div>
							)}
						</div>
					) : (
						<div className="reply-box pending">
							<h4>Teacher Reply</h4>
							<p>Not replied yet.</p>
						</div>
					)}

          <div className="meta">
            <span>Status: {d.status}</span>
            <span>Teacher: {d.teacherName}</span>
          </div>
        </div>
      ))}
    </div>
  );
}

export default StudentDoubts;
