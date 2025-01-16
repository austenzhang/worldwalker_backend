package com.example.VacationPlanner.controller;

import com.example.VacationPlanner.request.DebugRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DebugController {

    @PostMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("POST Test successful!");
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        System.out.println("Request received");
        return ResponseEntity.ok("GET Test successful!");
    }

}
