package com.olesya.psyCab.token;

import com.olesya.psyCab.user.User;

import java.util.Optional;

public interface VerificationTokenService {

    String validateToken(String token);

    void saveVerificationTokenForUser(User theUser, String token);

    Optional<VerificationToken> findByToken(String token);
}
