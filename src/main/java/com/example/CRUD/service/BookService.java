package com.example.CRUD.service;

import com.example.CRUD.model.dto.SearchValueDTO;
import com.example.CRUD.model.entity.Books;
import com.example.CRUD.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAllBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "publicationYear"));
    }

    public Books getBookById(int id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Books createBook(Books book) {
        return bookRepository.save(book);
    }

    public void updateBook(int id, Books book) {
        Books bk = bookRepository.findById(id).orElseThrow();
        bk.setTitle(book.getTitle());
        bk.setPublicationYear(book.getPublicationYear());
        bk.setGenreId(book.getGenreId());
        bookRepository.save(bk);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }


}
