package com.olesya.psyCab.token;

import com.olesya.psyCab.entity.PasswordResetToken;
import com.olesya.psyCab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
    void deleteByUserUserId(Long userId);
}
