package com.example.CRUD.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class BookAuthors {
    @EmbeddedId
    private BookAuthorID id;
}
