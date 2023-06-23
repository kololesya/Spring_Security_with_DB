package com.olesya.psyCab.registration.password;

import com.olesya.psyCab.user.User;

import java.util.List;
import java.util.Optional;

public interface PasswordResetTokenService {
    String validatePasswordResetToken(String theToken);

    List<PasswordResetToken> getAllTokens();
    void saveResetTokenForUser(User theUser, String token);
    void saveNewPassword(User theUser, String newPassword);
    Optional<User> findUserByPasswordResetToken(String theToken);

    //void createPasswordResetTokenForUser(User theUser, String token);
}
