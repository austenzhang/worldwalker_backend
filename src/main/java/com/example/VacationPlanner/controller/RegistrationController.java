package com.example.VacationPlanner.controller;

import com.example.VacationPlanner.model.VacationAppUser;
import com.example.VacationPlanner.repository.VacationAppUserRepository;
import com.example.VacationPlanner.request.RegisterUserRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    VacationAppUserRepository vacationAppUserRepository;

    public RegistrationController(VacationAppUserRepository vacationAppUserRepository) {
        this.vacationAppUserRepository = vacationAppUserRepository;
    }

    @PostMapping()
    public ResponseEntity<Object> registerUser(@RequestBody @Valid RegisterUserRequest request) {
        System.out.println("Request received");
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            VacationAppUser newUser = new VacationAppUser(request.getUsername(),
                    hashedPassword,
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getAge()
            );
            vacationAppUserRepository.save(newUser);
            return ResponseEntity.ok(Map.of("message", "Registration Successful"));
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.status(409).body(Map.of("error", "Username or email already exists."));
        }catch(Exception e){
            return ResponseEntity.status(500).body(Map.of("error", "Registration failed, unexpected error."));
        }
    }



}
