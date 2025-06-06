package com.example.CRUD.controller;

import com.example.CRUD.model.entity.BookCopies;
import com.example.CRUD.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookcopy")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping
    public Iterable<BookCopies> getAllBookCopies() {
        return bookCopyService.getAllBookCopies();
    }

    @GetMapping("/active")
    public Iterable<BookCopies> getAllAvailableCopy(){
        return bookCopyService.getAllAvailableCopy();
    }

    @GetMapping("/{id}")
    public BookCopies getBookCopyById(@PathVariable int id) {
        return bookCopyService.getBookCopyById(id);
    }

    @PostMapping
    public BookCopies createBookCopy(@RequestBody BookCopies bookCopy) {
        return bookCopyService.createBookCopy(bookCopy);
    }

    @PutMapping("/{id}")
    public void updateBookCopy(@PathVariable int id, @RequestBody BookCopies bookCopy) {
        bookCopyService.updateBookCopy(id, bookCopy);
    }

    @DeleteMapping("/{id}")
    public void deleteBookCopy(@PathVariable int id) {
        bookCopyService.deleteBookCopies(id);
    }
}
