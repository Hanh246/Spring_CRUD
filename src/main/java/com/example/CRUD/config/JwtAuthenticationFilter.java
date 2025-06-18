package com.example.CRUD.config;

import com.example.CRUD.repository.BorrowerRepository;
import com.example.CRUD.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/login") || path.equals("/logout") || (path.equals("/borrower")
        && request.getMethod().equalsIgnoreCase("POST"))) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if(!jwtService.validateToken(token)) log.info("Token is Invalid");
            if (jwtService.validateToken(token)) {
                if(jwtService.isTokenExpired(token)) log.info("Token is expired");
                if (!jwtService.isTokenExpired(token)) {
                    String username = jwtService.getUsernameFromToken(token);
                    if (username != null && borrowerRepository.findByName(username).isPresent()) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                username, null, Collections.emptyList());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                        return;
                    }
                    writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid username");
                    return;
                }
                writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
                return;
            }
            writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            return;
        }
        writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
    }

    private void writeErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
