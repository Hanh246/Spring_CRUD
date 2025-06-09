package com.example.CRUD.service;

import com.example.CRUD.model.entity.*;
import com.example.CRUD.repository.AuthorRepository;
import com.example.CRUD.repository.BookAuthorRepository;
import com.example.CRUD.repository.BookRepository;
import com.example.CRUD.repository.GenreRepository;
import com.example.CRUD.util.ExcelValidator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class ImportExcel {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private ExcelValidator excelValidator;

    public void saveExcelData(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            List<String> sheetNames = Arrays.asList("Books", "Authors");
            if (!excelValidator.validateSheetName(workbook, sheetNames)) {
                throw new RuntimeException("Invalid sheet name!");
            }

            //Sheet authors
            Sheet authorsSheet = workbook.getSheetAt(1);
            List<String> authorColumnNames = Arrays.asList("Name", "Birth year");
            if (!excelValidator.validateColumnNames(authorsSheet, authorColumnNames)) {
                throw new RuntimeException("Invalid Author column names!");
            }

            for (int i = 1; i <= authorsSheet.getLastRowNum(); i++) {
                Row row = authorsSheet.getRow(i);
                String authorName = row.getCell(1).getStringCellValue().trim();
                int birthYear = (int) row.getCell(2).getNumericCellValue();
                if(authorRepository.existsByName(authorName)){
                    continue;
                }
                authorRepository.save(new Authors(authorName, birthYear));
            }

            //Sheet books
            Sheet sheet = workbook.getSheetAt(0);
            List<String> bookColumnNames = Arrays.asList("Title", "Publication Year", "Author" , "Genre");
            if (!excelValidator.validateColumnNames(sheet, bookColumnNames)) {
                throw new RuntimeException("Invalid Book column names!");
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String title = row.getCell(1).getStringCellValue();
                int publicationYear = (int) row.getCell(2).getNumericCellValue();
                String authorName = row.getCell(3).getStringCellValue();
                String genreName = row.getCell(4).getStringCellValue();
                if(bookRepository.existsByTitle(title)){
                    continue;
                }

                Integer genreId = genreRepository.getGenresIdByName(genreName);
                System.out.println("first ID: "+ genreId);
                if (!genreRepository.existsByGenreName(genreName)) {
                    Genres newGenre = genreRepository.save(new Genres(genreName));
                    genreId = newGenre.getGenreId();
                    System.out.println("New genre ID: "+genreId);
                }
                System.out.println("Second ID: "+genreId);

                // Save book
                Books book = bookRepository.save(new Books(title, publicationYear, genreId));
                int bookId = book.getBookId();

                // authors
                String[] authors = authorName.split(",\\s*");
                for (String author : authors) {
                    author = author.trim();
                    Integer authorId = authorRepository.getAuthorIdByName(author);

                    if (!authorRepository.existsByName(author)) {
                        Authors newAuthor = authorRepository.save(new Authors(author));
                        authorId = newAuthor.getAuthorId();
                    }

                    // Create book author
                    BookAuthorID bookAuthorID = new BookAuthorID(bookId, authorId);
                    bookAuthorRepository.save(new BookAuthors(bookAuthorID));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("File could not be uploaded. Error: " + e.getMessage());
        }
    }
}
