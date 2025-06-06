package com.example.CRUD.repository;

import com.example.CRUD.model.entity.BookCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopies, Integer> {
    @Query( value = "Select * From BookCopies Where Status = 'Available'", nativeQuery = true)
    List<BookCopies> findAllAvailableCopy();
}
