package com.example.CRUD.service;

import com.example.CRUD.model.entity.BorrowHistory;
import com.example.CRUD.repository.BorrowHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowHistoryService {
    @Autowired
    private BorrowHistoryRepository borrowHistoryRepository;

    public Iterable<BorrowHistory> getAllBorrowHistory(){
        return borrowHistoryRepository.findAll();
    }
    public BorrowHistory getBorrowHistoryById(int id){
        return borrowHistoryRepository.findById(id).orElseThrow();
    }
    public void deleteBorrowHistoryById(int id){
        borrowHistoryRepository.deleteById(id);
    }
    public BorrowHistory createBorrowHistory(BorrowHistory borrowHistory){
        return borrowHistoryRepository.save(borrowHistory);
    }
    public void updateBorrowHistory(int id, BorrowHistory borrowHistory){
        BorrowHistory bor = borrowHistoryRepository.findById(id).orElseThrow();
        bor.setCopyId(borrowHistory.getCopyId());
        bor.setBorrowerId(borrowHistory.getBorrowerId());
        bor.setBorrowDate(borrowHistory.getBorrowDate());
        bor.setReturnDate(borrowHistory.getReturnDate());
        borrowHistoryRepository.save(bor);
    }
}
