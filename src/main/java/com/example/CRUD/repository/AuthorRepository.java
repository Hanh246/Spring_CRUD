package com.example.CRUD.repository;

import com.example.CRUD.model.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Authors, Integer> {
    @Query(value = "Select AuthorId\n" +
            "From Authors\n" +
            "Where Name = :name", nativeQuery = true)
    Integer getAuthorIdByName(String name);
    boolean existsByName(String name);
}
