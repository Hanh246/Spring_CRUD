package com.example.CRUD.controller;

import com.example.CRUD.model.entity.Authors;
import com.example.CRUD.service.AuthorService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Iterable<Authors> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Authors getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id).orElseThrow();
    }

    @PostMapping
    public Authors createAuthor(@RequestBody Authors author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable int id, @RequestBody Authors author) {
        authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
    }
}
