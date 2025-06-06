package com.example.CRUD.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    private int publicationYear;
    private int genreId;
}
