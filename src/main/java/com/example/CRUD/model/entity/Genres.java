package com.example.CRUD.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreId;
    private String genreName;

    public Genres(String genreName) {
        this.genreName = genreName;
    }
}
