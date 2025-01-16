package com.example.VacationPlanner.controller;

import com.example.VacationPlanner.config.JwtService;
import com.example.VacationPlanner.model.VacationAppUser;
import com.example.VacationPlanner.model.VacationRequest;
import com.example.VacationPlanner.repository.VacationAppUserRepository;
import com.example.VacationPlanner.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vacation/history")
public class HistoryController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private VacationAppUserRepository vacationAppUserRepository;

    @GetMapping
    public ResponseEntity<?> getHistory(@RequestHeader("Authorization") String authorizationHeader){
        try {
            String token = authorizationHeader.substring(7);
            String username = jwtService.extractUsername(token);

            VacationAppUser user = vacationAppUserRepository.findByUsername(username).get();

            List<VacationRequest> vacationRequests = user.getVacationRequests();
            return ResponseEntity.ok(vacationRequests);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token format");
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error has occurred.");
        }
    }

}
