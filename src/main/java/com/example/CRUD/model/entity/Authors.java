package com.example.CRUD.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String name;
    private int birthYear;

    public Authors(String author) {
        this.name = author;
    }

    public Authors(String authorName, int birthYear) {
        this.name = authorName;
        this.birthYear = birthYear;
    }
}
