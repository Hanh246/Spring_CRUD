package com.example.CRUD.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    private int publicationYear;
    @Column(name = "genreId", insertable = false, updatable = false)
    private int genreId;

    public Books(String title, int publicationYear, int genreId) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.genreId = genreId;
    }

    @OneToMany(mappedBy = "bookId")
    private List<BookCopies> bookCopies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genres genre;

    @ManyToMany
    @JoinTable(name = "BookAuthors",
            joinColumns = @JoinColumn(name = "BookId"),
            inverseJoinColumns = @JoinColumn(name = "AuthorId"))
    private List<Authors> authors = new ArrayList<>();
}
