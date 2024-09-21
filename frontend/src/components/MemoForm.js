import React, { useState } from "react";

const MemoForm = ({ onAddMemo }) => {
  const [memo, setMemo] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(memo);
    fetch("http://localhost:8080/api/memos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ memo }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((newMemo) => {
        onAddMemo(newMemo);
        setMemo("");
      })
      .catch((error) => {
        console.error("There was a problem with the fetch operation:", error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={memo}
        onChange={(e) => setMemo(e.target.value)}
        maxLength="20"
        required
      />
      <button type="submit">追加</button>
    </form>
  );
};

export default MemoForm;
