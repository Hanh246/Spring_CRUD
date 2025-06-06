package com.example.CRUD.controller;

import com.example.CRUD.model.entity.Books;
import com.example.CRUD.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public Iterable<Books> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Books getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Books createBook(@RequestBody Books book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable int id, @RequestBody Books book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
