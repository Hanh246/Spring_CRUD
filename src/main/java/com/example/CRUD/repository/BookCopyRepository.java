package com.example.CRUD.repository;

import com.example.CRUD.model.entity.BookCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopies, Integer> {
    @Query( value = "Select * From BookCopies Where Status = 'Available'", nativeQuery = true)
    List<BookCopies> findAllAvailableCopy();
    @Query( value = "Select COUNT(*)\n" +
            "From BookCopies \n" +
            "Where BookId = :id", nativeQuery = true)
    int getBookCopiesQuantityById(int id);
    @Query( value = "select COUNT(*)\n" +
            "From BookCopies bc join Books b on bc.BookId = b.BookId\n" +
            "Where b.Title = :title", nativeQuery = true)
    int getBookCopiesQuantityByBookTitle(String title);
    @Query( value = "Select * From BookCopies Where BookId = :id", nativeQuery = true)
    List<BookCopies> getBookCopiesByBookId(int id);
}
