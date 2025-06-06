package com.example.CRUD.service;

import com.example.CRUD.model.entity.Authors;
import com.example.CRUD.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Authors> getAllAuthors(){
        return authorRepository.findAll();
    }
    public Optional<Authors> getAuthorById(int id){
        return authorRepository.findById(id);
    }
    public Authors createAuthor(Authors author){
        return authorRepository.save(author);
    }
    public void updateAuthor(int id, Authors author){
        Authors aut = authorRepository.findById(id).orElseThrow();
        aut.setName(author.getName());
        aut.setBirthYear(author.getBirthYear());
        authorRepository.save(aut);
    }
    public void deleteAuthor(int id){
        authorRepository.deleteById(id);
    }

}
