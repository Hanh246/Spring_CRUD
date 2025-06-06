package com.example.CRUD.repository;

import com.example.CRUD.model.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Authors, Integer> {
}
