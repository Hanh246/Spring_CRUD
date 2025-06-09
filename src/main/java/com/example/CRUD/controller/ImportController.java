package com.example.CRUD.controller;

import com.example.CRUD.service.ImportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import")
public class ImportController {
    @Autowired
    private ImportExcel importExcel;
    @PostMapping
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty File!");
        }

        importExcel.saveExcelData(file);
        return ResponseEntity.ok("Upload and Save Successful.");
    }
}
