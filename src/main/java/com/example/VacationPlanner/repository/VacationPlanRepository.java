package com.example.VacationPlanner.repository;

import com.example.VacationPlanner.model.VacationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationPlanRepository extends JpaRepository<VacationPlan, Long> {
}
