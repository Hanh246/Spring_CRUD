package com.example.CRUD.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCopies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int copyId;
    private int bookId;
    private String status;

    public BookCopies(int bookId, String available) {
        this.bookId = bookId;
        this.status = available;
    }
}
