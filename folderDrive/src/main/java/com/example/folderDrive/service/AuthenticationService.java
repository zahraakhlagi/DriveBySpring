package com.example.folderDrive.service;


import com.example.folderDrive.dto.AuthenticationRequest;

import com.example.folderDrive.dto.AuthenticationResponse;
import com.example.folderDrive.dto.RegisterRequest;
import com.example.folderDrive.model.Role;
import com.example.folderDrive.model.User;
import com.example.folderDrive.repositoreis.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        //first create a new user
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        //save the user in db
        userRepository.save(user);
        //generate the token

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        //call the authentication manager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        //to find a user by email
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email can not be find"));
        //generate the token
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
