package com.example.CRUD.controller;

import com.example.CRUD.model.dto.BorrowerDTO;
import com.example.CRUD.model.dto.LoginDTO;
import com.example.CRUD.model.entity.Borrower;
import com.example.CRUD.repository.BorrowerRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/")
public class LoginController {
    @Autowired
    private BorrowerRepository bor;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @RequestBody LoginDTO login) {
        log.info("id: {}, email: {}", login.getName(), login.getEmail());
        System.out.println("Login inf: " + login.getName() + " " + login.getEmail());
        Optional<Borrower> borr = bor.findByName(login.getName());
        if (borr.isPresent()) {
            Borrower borrower = borr.get();
            if (borrower.getEmail().equals(login.getEmail())) {
                BorrowerDTO dto = new BorrowerDTO(borrower.getBorrowerId(), borrower.getName());
                session.setAttribute("borrower", dto);
                return ResponseEntity.ok(Map.of("status", "success",
                        "message", "Login successful",
                        "borrower", dto));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "status", "error",
                "message", "Invalid username or password"
        ));
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("borrower");
    }
}
