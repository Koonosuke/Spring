import React, { useState } from "react";
import MemoForm from "./components/MemoForm";
import MemoList from "./components/MemoList";

const App = () => {
  const [memos, setMemos] = useState([]);

  const addMemo = (newMemo) => {
    setMemos([...memos, newMemo]);
  };

  return (
    <div>
      <h1>メモアプリ</h1>
      <MemoForm onAddMemo={addMemo} />
      <MemoList memos={memos} setMemos={setMemos} />
    </div>
  );
};

export default App;
