package com.example.CRUD.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookCopies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CopyId;
    private int BookId;
    private String Status;
}
