package com.example.folderDrive.Controller;

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

    //this endpoint is to register the new user
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));

    }

    //this endpoint is to log in user
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticateRequest request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));

    }


}
