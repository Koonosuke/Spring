package com.example.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.memo.model.Memo;
import com.example.memo.service.MemoService;

@RestController
@RequestMapping("/api/memos")
@CrossOrigin(origins = "http://localhost:3000")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @GetMapping
    public List<Memo> getAllMemos() {
        return memoService.getAllMemos();
    }

    @PostMapping
    public ResponseEntity<Memo> addMemo(@RequestBody Memo memo) {
        if (memo == null || memo.getMemo() == null || memo.getMemo().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 不正なリクエスト
        }

        try {
            Memo savedMemo = memoService.addMemo(memo);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMemo); // 201 Created
        } catch (Exception e) {
            // エラーログを出力
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
    }
}