package com.example.folderDrive.controller;

import com.example.folderDrive.dto.AuthenticateRequest;
import com.example.folderDrive.dto.AuthenticationResponse;
import com.example.folderDrive.dto.RegisterRequest;
import com.example.folderDrive.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
         @Valid @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticateRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));

    }



}
