package com.example.VacationPlanner.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class VacationAppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String email;
    private String age;

    @JsonIgnore
    @OneToMany(mappedBy = "vacationAppUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<VacationRequest> vacationRequests;

    public VacationAppUser(String username, String password, String firstName, String lastName, String email, String age) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.vacationRequests = new ArrayList<VacationRequest>();
        this.age = age;
    }

    public VacationAppUser() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<VacationRequest> getVacationRequests() {
        return vacationRequests;
    }

    public void setVacationRequests(ArrayList<VacationRequest> vacationRequests) {
        this.vacationRequests = vacationRequests;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }
}
