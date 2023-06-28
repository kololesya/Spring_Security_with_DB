package com.olesya.psyCab.repository;

import com.olesya.psyCab.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Optional<Specialist> findByLastName(String lastName);

    Optional<Specialist> findBySpecialization(String specialization);

    Optional<Specialist> findByFirstName(String firstName);

    Boolean existsByFirstName(String firstName);

    Boolean existsByLastName(String lastName);

    Boolean existsBySpecialization(String specialization);
}
