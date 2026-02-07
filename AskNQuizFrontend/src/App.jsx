import { BrowserRouter, Routes, Route } from "react-router-dom";

/* ===== Student Auth ===== */
import StudentRegister from "./components/pages/auth/StudentRegister";
import StudentLogin from "./components/pages/auth/StudentLogin";

/* ===== Student Pages ===== */
import StudentDashboard from "./components/student/StudentDashboard";
import QuizList from "./components/student/QuizList";
import QuizAttemptStart from "./components/student/QuizAttemptStart";
import QuizQuestions from "./components/student/QuizQuestions";
import QuizResult from "./components/student/QuizResult";


/* ===== Admin Pages ===== */
import AdminLogin from "./components/pages/admin/AdminLogin";
import AdminDashboard from "./components/pages/admin/AdminDashboard";
import AddTeacher from "./components/pages/admin/AddTeacher";
import StudentList from "./components/pages/admin/StudentList";
import TeacherList from "./components/pages/admin/TeacherList";

/* ===== Notice Pages ===== */
import PostNotice from "./components/pages/notice/PostNotice";
import NoticeList from "./components/pages/notice/NoticeList";

/* ===== Doubt Pages ===== */
import AskDoubt from "./components/pages/doubt/AskDoubt";
import StudentDoubts from "./components/pages/doubt/StudentDoubts";
import ReplyDoubt from "./components/pages/doubt/ReplyDoubt";

/* ===== Teacher Pages ===== */
import TeacherLogin from "./components/teacher/TeacherLogin";
import TeacherDashboard from "./components/teacher/TeacherDashboard";

/* ===== Teacher Quiz Pages ===== */
import CreateQuiz from "./components/pages/quiz/CreateQuiz";
import AddQuestions from "./components/pages/quiz/AddQuestions";
import ReleaseQuiz from "./components/pages/quiz/ReleaseQuiz";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* Home */}
        <Route path="/" element={<h2>Welcome to AskNQuiz</h2>} />

        {/* Student Auth */}
        <Route path="/login" element={<StudentLogin />} />
        <Route path="/student/register" element={<StudentRegister />} />

        {/* Student */}
        <Route path="/student" element={<StudentDashboard />} />
        <Route path="/student/quizzes" element={<QuizList />} />
        <Route path="/student/result/:attemptId" element={<QuizResult />} />


        {/* 🔥 IMPORTANT ORDER */}
        <Route path="/student/attempt/:quizId" element={<QuizAttemptStart />} />
        <Route
          path="/student/attempt/:quizId/questions"
          element={<QuizQuestions />}
        />

        {/* Student Doubts */}
        <Route path="/student/ask-doubt" element={<AskDoubt />} />
        <Route path="/student/my-doubts" element={<StudentDoubts />} />

        {/* Admin */}
        <Route path="/admin/login" element={<AdminLogin />} />
        <Route path="/admin/dashboard" element={<AdminDashboard />} />
        <Route path="/admin/add-teacher" element={<AddTeacher />} />
        <Route path="/admin/students" element={<StudentList />} />
        <Route path="/admin/teachers" element={<TeacherList />} />

        {/* Notices */}
        <Route path="/teacher/post-notice" element={<PostNotice />} />
        <Route path="/notices" element={<NoticeList />} />

        {/* Teacher */}
        <Route path="/teacher/login" element={<TeacherLogin />} />
        <Route path="/teacher/dashboard" element={<TeacherDashboard />} />
        <Route path="/teacher/create-quiz" element={<CreateQuiz />} />
        <Route path="/teacher/add-questions" element={<AddQuestions />} />
        <Route path="/teacher/release-quiz" element={<ReleaseQuiz />} />
        <Route path="/teacher/reply-doubt" element={<ReplyDoubt />} />


        

        {/* Fallback */}
        <Route path="*" element={<h2>Page Not Found</h2>} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
