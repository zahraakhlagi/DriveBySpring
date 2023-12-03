package com.example.folderDrive.controller;

import com.example.folderDrive.dto.AuthenticateRequest;
import com.example.folderDrive.dto.AuthenticationResponse;
import com.example.folderDrive.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
         @Valid @RequestBody RegisterRequest request
    ){

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody AuthenticateRequest request
    ){

    }



}
