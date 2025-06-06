package com.example.CRUD.controller;

import com.example.CRUD.model.entity.BookAuthors;
import com.example.CRUD.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookauthor")
public class BookAuthorController {
    @Autowired
    private BookAuthorService bookAuthorService;

    @GetMapping
    public Iterable<BookAuthors> getAllBookAuthors() {
        return bookAuthorService.getAllBookAuthors();
    }

    @GetMapping("/{bookId}/{authorId}")
    public BookAuthors getBookAuthorById(@PathVariable int bookId, @PathVariable int authorId) {
        return bookAuthorService.getBookAuthorById(bookId, authorId);
    }

    @GetMapping("/{bookId}")
    public String getAuthorNameByBookId(@PathVariable int bookId){
        return bookAuthorService.getAuthorNameByBookId(bookId);
    }

    @PostMapping
    public BookAuthors createBookAuthor(@RequestBody BookAuthors bookAuthor) {
        return bookAuthorService.createBookAuthor(bookAuthor);
    }

    @PutMapping("/{bookId}/{authorId}")
    public void updateBookAuthor(@PathVariable int bookId, @PathVariable int authorId, @RequestBody BookAuthors bookAuthor) {
        bookAuthorService.updateBookAuthor(bookId, authorId, bookAuthor);
    }

    @DeleteMapping("/{bookId}/{authorId}")
    public void deleteBookAuthor(@PathVariable int bookId, @PathVariable int authorId) {
        bookAuthorService.deleteBookAuthor(bookId, authorId);
    }
}
