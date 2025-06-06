package com.example.CRUD.repository;

import com.example.CRUD.model.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
    Optional<Borrower> findByName(String Name);
}
