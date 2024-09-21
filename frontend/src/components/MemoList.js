import React, { useEffect, useState } from "react";

const MemoList = () => {
  const [memos, setMemos] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/memos")
      .then((response) => response.json())
      .then((data) => setMemos(data));
  }, []);

  const deleteMemo = (id) => {
    fetch(`http://localhost:8080/api/memos/${id}`, {
      method: "DELETE",
    }).then(() => setMemos(memos.filter((memo) => memo.id !== id)));
  };

  return (
    <div>
      <h2>メモ一覧</h2>
      <ul>
        {memos.map((memo) => (
          <li key={memo.id}>
            {memo.memo}
            <button onClick={() => deleteMemo(memo.id)}>削除</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default MemoList;
