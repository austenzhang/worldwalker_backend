package com.example.VacationPlanner.repository;

import com.example.VacationPlanner.model.VacationAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface VacationAppUserRepository extends JpaRepository<VacationAppUser, Long> {

    Optional<VacationAppUser> findByUsername(String username);

    Optional<VacationAppUser> findByEmail(String email);
}
