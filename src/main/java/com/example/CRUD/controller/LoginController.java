package com.example.CRUD.controller;

import com.example.CRUD.model.dto.BorrowerDTO;
import com.example.CRUD.model.dto.LoginDTO;
import com.example.CRUD.model.entity.Borrower;
import com.example.CRUD.repository.BorrowerRepository;
import com.example.CRUD.service.JwtService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/")
public class LoginController {
    @Autowired
    private BorrowerRepository bor;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @Valid @RequestBody LoginDTO login,
                                   BindingResult result) {
        log.info("id: {}, passwords: {}", login.getName(), login.getPassword());
        if (result.hasErrors()) {
            String message = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", message));
        }
        Optional<Borrower> borr = bor.findByName(login.getName());
        if (borr.isPresent()) {
            Borrower borrower = borr.get();
            if (passwordEncoder.matches(login.getPassword(), borrower.getPassword())) {
                String token = jwtService.generateToken(borrower.getName());
                BorrowerDTO dto = new BorrowerDTO(borrower.getBorrowerId(), borrower.getName());
                session.setAttribute("borrower", dto);
                return ResponseEntity.ok(Map.of("status", "success",
                        "message", "Login successful",
                        "borrower", dto,
                        "token", "Bearer " + token));
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
