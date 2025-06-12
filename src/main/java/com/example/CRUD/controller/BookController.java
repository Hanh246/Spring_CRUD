package com.example.CRUD.controller;

import com.example.CRUD.model.dto.BookDTO;
import com.example.CRUD.model.entity.Authors;
import com.example.CRUD.model.entity.Books;
import com.example.CRUD.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<BookDTO> pageBooks = bookService.getAllBooks(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("books", pageBooks.getContent());
        response.put("currentPage", pageBooks.getNumber());
        response.put("totalPages", pageBooks.getTotalPages());
        response.put("totalItems", pageBooks.getTotalElements());
        return ResponseEntity.ok(response);
    }   

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/authors/{id}")
    public String getAuthorNameByBookId(@PathVariable int id){
        return bookService.getAuthorsByBook(id);
    }

    @PostMapping
    public Books createBook(@RequestBody BookDTO book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable int id, @RequestBody BookDTO book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
