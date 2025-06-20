package com.example.CRUD.repository;

import com.example.CRUD.model.entity.Authors;
import com.example.CRUD.model.entity.BookAuthorID;
import com.example.CRUD.model.entity.BookAuthors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthors, BookAuthorID> {
    @Query(value = "Select STRING_AGG(a.Name, ', ') AS AuthorNames\n" +
            "From BookAuthors b join Authors a on b.AuthorId = a.AuthorId\n" +
            "Where b.BookId = :id", nativeQuery = true)
    String getAuthorNameByBookId(int id);

    @Query(value = "Select a.AuthorId, a.BirthYear, a.Name\n" +
            "From BookAuthors b join Authors a on b.AuthorId = a.AuthorId\n" +
            "Where b.BookId = :id", nativeQuery = true)
    List<Authors> getAuthorByBookId(int id);
    @Query(value = "Select *\n" +
            "From BookAuthors\n" +
            "Where BookId = :id", nativeQuery = true)
    List<BookAuthors> getBookAuthorByBookId(int id);
}
