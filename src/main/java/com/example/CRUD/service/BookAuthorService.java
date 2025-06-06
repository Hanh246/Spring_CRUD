package com.example.CRUD.service;

import com.example.CRUD.model.entity.BookAuthorID;
import com.example.CRUD.model.entity.BookAuthors;
import com.example.CRUD.repository.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuthorService {
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    public List<BookAuthors> getAllBookAuthors(){
        return bookAuthorRepository.findAll();
    }
    public BookAuthors getBookAuthorById(int bookId, int authorId){
        return bookAuthorRepository.findById(new BookAuthorID(bookId, authorId)).orElseThrow();
    }
    public String getAuthorNameByBookId(int bookId){
        return bookAuthorRepository.getAuthorNameByBookId(bookId);
    }
    public BookAuthors createBookAuthor(BookAuthors bookAuthor){
        return bookAuthorRepository.save(bookAuthor);
    }
    public void updateBookAuthor(int bookId, int authorId,BookAuthors bookAuthor){
        BookAuthorID id = new BookAuthorID(bookId, authorId);
        BookAuthors aut = bookAuthorRepository.findById(id).orElseThrow();
        aut.setId(id);
        bookAuthorRepository.save(aut);
    }
    public void deleteBookAuthor(int bookid, int authorid){
        bookAuthorRepository.deleteById(new BookAuthorID(bookid, authorid));
    }
}
