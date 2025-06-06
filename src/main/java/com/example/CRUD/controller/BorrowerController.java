package com.example.CRUD.controller;

import com.example.CRUD.model.dto.BorrowerDTO;
import com.example.CRUD.model.entity.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CRUD.service.BorrowerService;

import java.util.List;

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
    public Borrower createBorrower(@RequestBody Borrower borrower) {
        return borrowerService.createBorrower(borrower);
    }

    @PutMapping("/{id}")
    public void updateBorrower(@PathVariable int id, @RequestBody Borrower borrower) {
        borrowerService.updateBorrower(id, borrower);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrower(@PathVariable int id) {
        borrowerService.deleteBorrower(id);
    }
}

