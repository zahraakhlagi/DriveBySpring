package com.example.folderDrive.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain)
            throws ServletException, IOException {
       final String authHeader= request.getHeader("Authorization");
       final String jwt;
       final String userEmail;
        if (authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt= authHeader.substring(7);
        //userEmail=//todo extract the user email from jwt token
        userEmail= jwtService.extractUsername(jwt);

    }
}
