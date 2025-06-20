package com.example.CRUD.repository;

import com.example.CRUD.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Books, Integer> {
    @Query(value = "Select *\n" +
            "From Books\n" +
            "Where (:fromYear IS NULL OR PublicationYear >= :fromYear)\n" +
            "AND (:toYear IS NULL OR PublicationYear <= :toYear)", nativeQuery = true)
    List<Books> exportCondition(Integer fromYear, Integer toYear);

    @Query(value = "Select Top 1 BookId\n" +
            "From Books\n" +
            "Order By BookId desc", nativeQuery = true)
    int getLastestBookId();

    boolean existsByTitle(String title);

    @Query(value = "Select BookId\n" +
            "From Books\n" +
            "Where Title = :title", nativeQuery = true)
    int getBookIdByTitle(String title);
}
