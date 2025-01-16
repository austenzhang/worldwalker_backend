package com.example.VacationPlanner.repository;

import com.example.VacationPlanner.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
}
