package com.example.CRUD.repository;

import com.example.CRUD.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Integer> {
}
