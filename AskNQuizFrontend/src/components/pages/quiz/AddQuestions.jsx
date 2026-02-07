import { useState } from "react";
import axios from "axios";
import "./Quiz.css";

function AddQuestions() {
  const quizId = Number(localStorage.getItem("currentQuizId"));
  const quizStatus = localStorage.getItem("quizStatus");

  const [form, setForm] = useState({
    questionText: "",
    optionA: "",
    optionB: "",
    optionC: "",
    optionD: "",
    correctOption: "A"
  });

  if (!quizId) {
    return <h3>Please create a quiz first</h3>;
  }

 
  if (quizStatus === "RELEASED") {
    return <h3>This quiz is already released. You cannot add questions.</h3>;
  }

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const options = [
      { optionText: form.optionA, correct: form.correctOption === "A" },
      { optionText: form.optionB, correct: form.correctOption === "B" },
      { optionText: form.optionC, correct: form.correctOption === "C" },
      { optionText: form.optionD, correct: form.correctOption === "D" }
    ].filter(o => o.optionText.trim() !== "");

    if (options.length < 2) {
      alert("At least 2 options required");
      return;
    }

    if (!options.some(o => o.correct)) {
      alert("Please select a correct option");
      return;
    }

    try {
      await axios.post(
        `http://localhost:8080/questions/add/${quizId}`,
        {
          questionText: form.questionText,
          options
        }
      );

      alert("Question added successfully");

      setForm({
        questionText: "",
        optionA: "",
        optionB: "",
        optionC: "",
        optionD: "",
        correctOption: "A"
      });

    } catch (err) {
      alert(err.response?.data?.message || "Failed to add question");
    }
  };

  return (
    <div className="quiz-page">
      <div className="quiz-card">
        <h2>Add Question</h2>

        <form onSubmit={handleSubmit}>
          <input name="questionText" placeholder="Question" value={form.questionText} onChange={handleChange} required />
          <input name="optionA" placeholder="Option A" value={form.optionA} onChange={handleChange} required />
          <input name="optionB" placeholder="Option B" value={form.optionB} onChange={handleChange} required />
          <input name="optionC" placeholder="Option C" value={form.optionC} onChange={handleChange} />
          <input name="optionD" placeholder="Option D" value={form.optionD} onChange={handleChange} />

          <select name="correctOption" value={form.correctOption} onChange={handleChange}>
            <option value="A">Correct Option: A</option>
            <option value="B">Correct Option: B</option>
            <option value="C">Correct Option: C</option>
            <option value="D">Correct Option: D</option>
          </select>

          <button type="submit">Add Question</button>
        </form>
      </div>
    </div>
  );
}

export default AddQuestions;
