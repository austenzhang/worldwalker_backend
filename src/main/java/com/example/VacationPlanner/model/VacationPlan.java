package com.example.VacationPlanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class VacationPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @OneToOne
    @JoinColumn(name = "vacation_request_id", nullable = true)
    @JsonBackReference
    private VacationRequest vacationRequest;

    public VacationPlan(String body, VacationRequest vacationRequest) {
        this.body = body;
        this.vacationRequest = vacationRequest;
    }

    public VacationPlan() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public VacationRequest getVacationRequest() {
        return vacationRequest;
    }

    public void setVacationRequest(VacationRequest vacationRequest) {
        this.vacationRequest = vacationRequest;
    }
}
