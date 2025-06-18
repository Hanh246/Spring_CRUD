package com.example.CRUD.service;

import com.example.CRUD.model.dto.BookDTO;
import com.example.CRUD.model.entity.Authors;
import com.example.CRUD.model.entity.BookAuthorID;
import com.example.CRUD.model.entity.BookAuthors;
import com.example.CRUD.model.entity.Books;
import com.example.CRUD.repository.AuthorRepository;
import com.example.CRUD.repository.BookAuthorRepository;
import com.example.CRUD.repository.BookRepository;
import com.example.CRUD.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookAuthorService bookAuthorService;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Page<BookDTO> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable)
                .map(b -> new BookDTO(b.getBookId(), b.getTitle(), b.getGenre().getGenreName()
                        , b.getPublicationYear(), b.getBookCopies().size()
                        , b.getAuthors().stream().map(Authors::getName).collect(Collectors.joining(", "))));
    }

    public BookDTO getBookById(int id) {
        return bookRepository.findById(id)
                .map(b -> new BookDTO(b.getBookId(), b.getTitle(), b.getGenre().getGenreName()
                , b.getPublicationYear(), b.getBookCopies().size()
                , b.getAuthors().stream().map(Authors::getName).collect(Collectors.joining(", ")))).orElseThrow();
    }

    public String getAuthorsByBook(int id){
       BookDTO book = this.getBookById(id);
       return book.getAuthors();
    }

    public Books createBook(BookDTO bookDTO) {
        Books book = new Books();
        book.setTitle(bookDTO.getTitle());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setGenreId(genreRepository.getGenresIdByName(bookDTO.getGenre()));
        String[] authors = bookDTO.getAuthors().split(",\\s*");
        for (String author : authors) {
            author = author.trim();
            Integer authorId = authorRepository.getAuthorIdByName(author);
            if (!authorRepository.existsByName(author)) {
                Authors newAuthor = authorRepository.save(new Authors(author));
                authorId = newAuthor.getAuthorId();
            }
            // Create book author
            bookAuthorService.createBookAuthor(new BookAuthors(new BookAuthorID(book.getBookId(), authorId)));
        }
        return bookRepository.save(book);
    }

    public void updateBook(int id, BookDTO bookDTO) {
        Books bk = bookRepository.findById(id).orElseThrow();
        bk.setTitle(bookDTO.getTitle());
        bk.setPublicationYear(bookDTO.getPublicationYear());
        bk.setGenreId(genreRepository.getGenresIdByName(bookDTO.getGenre()));
        String[] authors = bookDTO.getAuthors().split(",\\s*");
        List<BookAuthors> bookAuthors = bookAuthorRepository.getBookAuthorByBookId(id);
        for (BookAuthors bookAuthor : bookAuthors) {
            bookAuthorService.deleteBookAuthor(bk.getBookId(), bookAuthor.getId().getAuthorId());
        }
        for (String author : authors) {
            bookAuthorService.createBookAuthor(new BookAuthors(new BookAuthorID(bk.getBookId(), authorRepository.getAuthorIdByName(author))));
        }
        bookRepository.save(bk);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

}
