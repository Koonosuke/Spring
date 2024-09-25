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
//REST（Representational State Transfer）APIは、HTTPプロトコルを使ってサーバーとクライアントがやり取りするための設計パターン
@RequestMapping("/api/memos")
@CrossOrigin(origins = "http://localhost:3000")
//Reactなど他のオリジンからのリクエストを許可するCORS（クロスオリジン）設定です。ここでは、http://localhost:3000（通常、React開発環境のデフォルトのURL）からのリクエストを許可しています。
public class MemoController {

    @Autowired
    private MemoService memoService;

    @GetMapping
    //HTTPのGETリクエストを処理するためのアノテーション。このメソッドは、/api/memos にアクセスしたときにすべてのメモを取得
    //メモの取得
    public List<Memo> getAllMemos() {
        return memoService.getAllMemos();
    }
    @PostMapping//HTTPのPOSTリクエストを処理
    public ResponseEntity<Memo> addMemo(@RequestBody Memo memo) {//リクエストのJSONデータを Memo オブジェクトに変換
        if (memo == null || memo.getMemo() == null || memo.getMemo().isEmpty()) {//isEmptyは空白で（文字として扱われない場合の例外処理）
            return ResponseEntity.badRequest().build(); // 不正なリクエスト
        }

        try {
            Memo savedMemo = memoService.addMemo(memo);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMemo); // 201 OK
        } catch (Exception e) {
            // エラーログ!!
            
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 エラー
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
    }
}