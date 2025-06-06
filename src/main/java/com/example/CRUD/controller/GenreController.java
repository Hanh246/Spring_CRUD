package com.example.CRUD.controller;

import com.example.CRUD.model.entity.Genres;
import com.example.CRUD.service.GenreService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public Iterable<Genres> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public Genres getGenreById(@PathVariable int id) {
        return genreService.getGenreById(id);
    }

    @GetMapping("/name/{id}")
    public String getGenreNameById(@PathVariable int id){
        return genreService.getGenreNameById(id);
    }

    @PostMapping
    public Genres createGenre(@RequestBody Genres genre) {
        return genreService.createGenre(genre);
    }

    @PutMapping("/{id}")
    public void updateGenre(@PathVariable int id, @RequestBody Genres genre) {
        genreService.updateGenre(id, genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable int id) {
        genreService.deleteGenre(id);
    }
}
