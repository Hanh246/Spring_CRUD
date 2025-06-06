package com.example.CRUD.controller;

import com.example.CRUD.model.dto.SearchValueDTO;
import com.example.CRUD.service.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.io.IOException;

@RestController
@RequestMapping("/export")
public class ExportController {
    @Autowired
    private ExportExcel exportExcel;

    @GetMapping
    public ResponseEntity<InputStreamResource> exportToExcel(@ModelAttribute SearchValueDTO searchValue) throws IOException {
        ByteArrayInputStream in = exportExcel.exportToExcel(searchValue.getFromYear(), searchValue.getToYear());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=books.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
}
