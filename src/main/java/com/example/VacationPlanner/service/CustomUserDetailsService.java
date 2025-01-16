//package com.example.VacationPlanner.service;
//
//import com.example.VacationPlanner.model.VacationAppUser;
//import com.example.VacationPlanner.repository.VacationAppUserRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final VacationAppUserRepository userRepository;
//
//    public CustomUserDetailsService(VacationAppUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        VacationAppUser user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword()) // Ensure passwords are encoded
//                .roles("USER") // Assign roles
//                .build();
//    }
//}
