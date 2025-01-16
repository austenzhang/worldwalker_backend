package com.example.VacationPlanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destination;
    private String startDate;
    private String endDate;
    private String partySize;
    private String tripContent;

    @ManyToOne
    @JoinColumn(name = "vacation_app_user_id", nullable = false)
    @JsonBackReference
    private VacationAppUser vacationAppUser;

    @OneToOne(mappedBy = "vacationRequest", cascade = CascadeType.ALL)
    @JsonManagedReference
    private VacationPlan vacationPlan;

    public VacationRequest(String dest, String startDate, String endDate, String partySize, String tripContent, VacationAppUser vacationAppUser) {
        this.destination = dest;
        this.startDate = startDate;
        this.endDate = endDate;
        this.partySize = partySize;
        this.tripContent = tripContent;
        this.vacationAppUser = vacationAppUser;
    }

    public VacationRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPartySize() {
        return partySize;
    }

    public void setPartySize(String partySize) {
        this.partySize = partySize;
    }

    public VacationAppUser getVacationAppUser() {
        return vacationAppUser;
    }

    public void setVacationAppUser(VacationAppUser vacationAppUser) {
        this.vacationAppUser = vacationAppUser;
    }

    public VacationPlan getVacationPlan() {
        return vacationPlan;
    }

    public void setVacationPlan(VacationPlan vacationPlan) {
        this.vacationPlan = vacationPlan;
    }

    public String getTripContent() {
        return tripContent;
    }

    public void setTripContent(String tripContent) {
        this.tripContent = tripContent;
    }
}
