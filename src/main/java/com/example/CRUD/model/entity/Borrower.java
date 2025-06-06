package com.example.CRUD.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Borrowers")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BorrowerId;
    private String name;
    private String email;
}
