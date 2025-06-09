package com.example.CRUD.repository;

import com.example.CRUD.model.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<Genres, Integer> {
    @Query(value = "Select GenreName\n" +
            "From Genres\n" +
            "Where GenreId = :id" , nativeQuery = true)
    String getGenreNameById(int id);

    @Query(value = "Select GenreId\n" +
            "From Genres\n" +
            "Where GenreName = :name", nativeQuery = true)
    Integer getGenresIdByName(String name);
    boolean existsByGenreName(String genreName);
}
