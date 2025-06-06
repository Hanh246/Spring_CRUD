package com.example.CRUD.service;

import com.example.CRUD.model.entity.Authors;
import com.example.CRUD.model.entity.Books;
import com.example.CRUD.repository.BookAuthorRepository;
import com.example.CRUD.repository.BookRepository;
import com.example.CRUD.repository.GenreRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExportExcel {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private GenreRepository genreRepo;
    @Autowired
    private BookAuthorRepository baRepo;

    public ByteArrayInputStream exportToExcel(int fromYear, int toYear) throws IOException {
        if (fromYear > toYear) {
            throw new IllegalArgumentException("fromYear cannot be greater than toYear");
        }
        if (fromYear < 0 || toYear < 0) {
            throw new IllegalArgumentException("Years cannot be negative");
        }
        List<Books> books = bookRepo.exportCondition(fromYear, toYear);

        if (books.isEmpty()) {
            throw new IllegalStateException("No books found for the given year range");
        }
        try (Workbook workbook = new XSSFWorkbook()) {
            int rowIdx = 1;
            int stt = 1;
            //Books sheet
            Sheet bookSheet = workbook.createSheet("Books");
            Row bookHeader = bookSheet.createRow(0);
            bookHeader.createCell(0).setCellValue("ID");
            bookHeader.createCell(1).setCellValue("Title");
            bookHeader.createCell(2).setCellValue("Publication Year");
            bookHeader.createCell(3).setCellValue("Author");
            bookHeader.createCell(4).setCellValue("Genre");

            for (Books book : books) {
                Row bookRow = bookSheet.createRow(rowIdx++);
                bookRow.createCell(0).setCellValue(stt++);
                bookRow.createCell(1).setCellValue(book.getTitle());
                bookRow.createCell(2).setCellValue(book.getPublicationYear());
                bookRow.createCell(3).setCellValue(baRepo.getAuthorNameByBookId(book.getBookId()));
                bookRow.createCell(4).setCellValue(genreRepo.getGenreNameById(book.getGenreId()));
            }
            //Author sheet
            Sheet authorSheet = workbook.createSheet("Authors");
            Row authorHeader = authorSheet.createRow(0);
            authorHeader.createCell(0).setCellValue("ID");
            authorHeader.createCell(1).setCellValue("Name");
            authorHeader.createCell(2).setCellValue("Birth year");

            Set<String> authorKeys = new HashSet<>();
            List<Authors> uniqueAuthors = new ArrayList<>();
            for(Books book : books){
                List<Authors> authors = baRepo.getAuthorByBookId(book.getBookId());
                for(Authors author : authors){
                    String key = author.getName();
                    if(authorKeys.add(key)){
                        uniqueAuthors.add(author);
                    }
                }
            }
            int AuRowIdx = 1;
            int auStt = 1;
            for (Authors author : uniqueAuthors){
                Row authorRow = authorSheet.createRow(AuRowIdx++);
                authorRow.createCell(0).setCellValue(auStt++);
                authorRow.createCell(1).setCellValue(author.getName());
                authorRow.createCell(2).setCellValue(author.getBirthYear());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
