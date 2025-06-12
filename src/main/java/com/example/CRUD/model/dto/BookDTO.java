package com.example.CRUD.model.dto;

import com.example.CRUD.model.entity.Authors;
import com.example.CRUD.model.entity.Genres;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    private int bookId;
    private String title;
    private String genre;
    private int publicationYear;
    private int copies;
    private String authors;
}
