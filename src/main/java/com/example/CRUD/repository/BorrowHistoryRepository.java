package com.example.CRUD.repository;

import com.example.CRUD.model.entity.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Integer> {
}
