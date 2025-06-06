package com.example.CRUD.service;

import com.example.CRUD.model.entity.BookCopies;
import com.example.CRUD.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public List<BookCopies> getAllBookCopies(){
        return bookCopyRepository.findAll();
    }
    public BookCopies getBookCopyById(int id){
        return bookCopyRepository.findById(id).orElseThrow();
    }
    public BookCopies createBookCopy(BookCopies bookCopy){
        return bookCopyRepository.save(bookCopy);
    }
    public void updateBookCopy(int id, BookCopies bookCopy){
        BookCopies bk = bookCopyRepository.findById(id).orElseThrow();
        bk.setBookId(bookCopy.getBookId());
        bk.setStatus(bookCopy.getStatus());
        bookCopyRepository.save(bk);
    }
    public List<BookCopies> getAllAvailableCopy(){
        return bookCopyRepository.findAllAvailableCopy();
    }
    public void deleteBookCopies(int id){
        bookCopyRepository.deleteById(id);
    }
}
