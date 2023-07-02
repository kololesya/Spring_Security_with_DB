package com.olesya.psyCab.repository;

import com.olesya.psyCab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    //void update(String firstName, String lastName, String email, Long id);
}
