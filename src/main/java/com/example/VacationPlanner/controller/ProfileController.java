package com.example.VacationPlanner.controller;

import com.example.VacationPlanner.config.JwtService;
import com.example.VacationPlanner.model.VacationAppUser;
import com.example.VacationPlanner.repository.VacationAppUserRepository;
import com.example.VacationPlanner.request.ProfileRequest;
import com.example.VacationPlanner.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/profile")
public class ProfileController {

    @Autowired
    private VacationAppUserRepository repo;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader){
        try {
            String token = authorizationHeader.substring(7);
            String username = jwtService.extractUsername(token);

            VacationAppUser user = repo.findByUsername(username).get();

            ProfileResponse response = new ProfileResponse(
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getAge()
            );

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token format");
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error has occured.");
        }
    }

    @PostMapping
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequest request, @RequestHeader("Authorization") String authorizationHeader){
        try {
            String token = authorizationHeader.substring(7);
            String username = jwtService.extractUsername(token);

            VacationAppUser user = repo.findByUsername(username).get();

            //can't edit username due to jwt token being tied to it.
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setAge(request.getAge());
            repo.save(user);

            ProfileResponse response = new ProfileResponse(
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getAge()
            );

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token format");
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error has occurred.");
        }
    }

}
