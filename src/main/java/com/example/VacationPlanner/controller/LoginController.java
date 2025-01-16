package com.example.VacationPlanner.controller;

import com.example.VacationPlanner.config.JwtService;
import com.example.VacationPlanner.request.LoginRequest;
import com.example.VacationPlanner.response.LoginResponse;
import com.example.VacationPlanner.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final AuthenticationService service;

    @Autowired
    private JwtService jwtService;


    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//            );
//
//            String token = jwtService.generateToken(request.getUsername());
//            return ResponseEntity.ok(Map.of("token", token)); // Respond with JWT token
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
//        }
        return ResponseEntity.ok(service.authenticate(request));
    }

}
