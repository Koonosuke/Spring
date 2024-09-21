package com.example.memo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.memo.model.Memo;
import com.example.memo.repository.MemoRepository;

@Service
public class MemoService {

    @Autowired
    private MemoRepository memoRepository;

    public List<Memo> getAllMemos() {
        return memoRepository.findAll();
    }

    public Memo addMemo(Memo memo) {
        return memoRepository.save(memo);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }
}
