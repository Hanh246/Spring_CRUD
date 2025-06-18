package com.example.CRUD.controller;

import com.example.CRUD.model.dto.BorrowerDTO;
import com.example.CRUD.model.entity.Borrower;
import jakarta.validation.Valid;
import org.apache.poi.ss.formula.functions.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.example.CRUD.service.BorrowerService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;

    @GetMapping
    public List<BorrowerDTO> getAllBorrowers() {
        return borrowerService.getAllBorrowers();
    }

    @GetMapping("/{id}")
    public Borrower getBorrowerById(@PathVariable int id) {
        return borrowerService.getBorrowerById(id).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<?> createBorrower(@RequestBody @Valid Borrower borrower, BindingResult bindResult) {
        if(bindResult.hasErrors()){
            String message = validateMessage(bindResult);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", message));
        }
        Borrower bor = borrowerService.createBorrower(borrower);
        return ResponseEntity.ok(Map.of("status", "success"
                , "borrower", bor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBorrower(@PathVariable int id, @RequestBody @Valid Borrower borrower , BindingResult bindResult) {
        if(bindResult.hasErrors()){
            String message = validateMessage(bindResult);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", message));
        }
        borrowerService.updateBorrower(id, borrower);
        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("/{id}")
    public void deleteBorrower(@PathVariable int id) {
        borrowerService.deleteBorrower(id);
    }

    private String validateMessage(BindingResult bindResult){
        return bindResult.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
    }
}

