package com.example.CRUD.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AuthorId;
    private String Name;
    private int BirthYear;
}
