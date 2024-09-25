package com.example.memo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Memo {

    @Id//データベースのテーブルと対応するエンティティであることを示す
    @GeneratedValue(strategy = GenerationType.IDENTITY)//idがデータベースで自動的に生成される
    private Long id;

    @Column(length = 20, nullable = false)
    
    private String memo;

    // Getter
    public String getMemo() {
        return memo;
    }

    // Setter
    public void setMemo(String memo) {
        this.memo = memo;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
