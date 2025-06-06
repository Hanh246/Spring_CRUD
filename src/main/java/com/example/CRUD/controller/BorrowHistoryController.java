package com.example.CRUD.controller;

import com.example.CRUD.model.entity.BorrowHistory;
import com.example.CRUD.service.BorrowHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class BorrowHistoryController {
    @Autowired
    private BorrowHistoryService borrowHistoryService;

    @GetMapping
    public Iterable<BorrowHistory> getAllBorrowHistory() {
        return borrowHistoryService.getAllBorrowHistory();
    }

    @GetMapping("/{id}")
    public BorrowHistory getBorrowHistoryById(@PathVariable int id) {
        return borrowHistoryService.getBorrowHistoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowHistoryById(@PathVariable int id) {
        borrowHistoryService.deleteBorrowHistoryById(id);
    }

    @PostMapping
    public BorrowHistory createBorrowHistory(@RequestBody BorrowHistory borrowHistory) {
        return borrowHistoryService.createBorrowHistory(borrowHistory);
    }

    @PutMapping("/{id}")
    public void updateBorrowHistory(@PathVariable int id, @RequestBody BorrowHistory borrowHistory) {
        borrowHistoryService.updateBorrowHistory(id, borrowHistory);
    }
}
