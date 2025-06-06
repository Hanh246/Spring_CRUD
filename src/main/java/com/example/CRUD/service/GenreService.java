package com.example.CRUD.service;

import com.example.CRUD.model.entity.Genres;
import com.example.CRUD.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genres> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genres getGenreById(int id) {
        return genreRepository.findById(id).orElseThrow();
    }
    public String getGenreNameById(int id){
        return genreRepository.getGenreNameById(id);
    }
    public Genres createGenre(Genres genre) {
        return genreRepository.save(genre);
    }

    public void updateGenre(int id, Genres genre) {
        Genres gen = genreRepository.findById(id).orElseThrow();
        gen.setGenreName(genre.getGenreName());
        genreRepository.save(gen);
    }

    public void deleteGenre(int id) {
        genreRepository.deleteById(id);
    }
}
