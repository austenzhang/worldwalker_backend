package com.example.VacationPlanner.service;


import com.example.VacationPlanner.config.JwtService;
import com.example.VacationPlanner.repository.VacationAppUserRepository;
import com.example.VacationPlanner.request.LoginRequest;
import com.example.VacationPlanner.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final VacationAppUserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public LoginResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

}
