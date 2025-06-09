package com.example.CRUD.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class BookAuthorID {
    private int BookId;
    private int AuthorId;

    public BookAuthorID(int bookId, int authorId) {
        this.BookId = bookId;
        this.AuthorId = authorId;
    }
    public void BookAuthors() {}
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookAuthorID other = (BookAuthorID) obj;
            return BookId == other.BookId && AuthorId == other.AuthorId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(BookId, AuthorId);
    }
}
