package com.example.CRUD.service;

import com.example.CRUD.model.dto.BorrowerDTO;
import com.example.CRUD.model.entity.Borrower;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CRUD.repository.BorrowerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<BorrowerDTO> getAllBorrowers() {
        log.info("Find {} user", borrowerRepository.findAll().size());
        return borrowerRepository.findAll().stream().
                map(b -> new BorrowerDTO(b.getBorrowerId(), b.getName())).
                collect(Collectors.toList());
    }

    public Optional<Borrower> getBorrowerById(int id) {
        return borrowerRepository.findById(id);
    }

    public void updateBorrower(int id, Borrower borrower) {
        Borrower bor = borrowerRepository.findById(id).orElseThrow();
        bor.setName(borrower.getName());
        bor.setEmail(borrower.getEmail());
        bor.setPassword(passwordEncoder.encode(borrower.getPassword()));
        borrowerRepository.save(bor);
    }

    public Borrower createBorrower(Borrower borrower) {
        borrower.setPassword(passwordEncoder.encode(borrower.getPassword()));
        return borrowerRepository.save(borrower);
    }

    public void deleteBorrower(int id) {
        borrowerRepository.deleteById(id);
    }
}
