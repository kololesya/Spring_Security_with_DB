package com.olesya.psyCab.request.password;

import com.olesya.psyCab.entity.PasswordResetToken;
import com.olesya.psyCab.repository.PasswordResetTokenRepository;
import com.olesya.psyCab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olesya.psyCab.entity.User;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository uRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String validatePasswordResetToken(String theToken) {
        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByToken(theToken);
        Calendar calendar = Calendar.getInstance();
        if (passwordResetToken.isEmpty()){
            return "invalid";
        } else if ((passwordResetToken.get().getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "expired";
        } else {
            return "valid";
        }
    }

    @Override
    public List<PasswordResetToken> getAllTokens() {
        return passwordResetTokenRepository.findAll();
    }

    @Override
    public void saveResetTokenForUser(User theUser, String token){
        PasswordResetToken newToken = new PasswordResetToken(token, theUser);
        Optional<PasswordResetToken> existUser = findTokenByPasswordResetToken(theUser);
        if (existUser.isPresent()){
            deletePasswordResetTokenForUser(theUser);
            passwordResetTokenRepository.save(newToken);
        } else {
        passwordResetTokenRepository.save(newToken);
        }
    }

    @Override
    public void saveNewPassword(User theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        uRepo.save(theUser);
    }

    @Override
    public Optional<User> findUserByPasswordResetToken(String theToken){
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(theToken).get().getUser());
    }

    @Override
    public void deletePasswordResetTokenForUser(User theUser) {
        Optional<PasswordResetToken> existUser = findTokenByPasswordResetToken(theUser);
        Long tokenId = existUser.get().getTokenId();
        passwordResetTokenRepository.deleteById(tokenId);
    }

    private Optional<PasswordResetToken> findTokenByPasswordResetToken(User theUser){
        Optional<PasswordResetToken> existUser = passwordResetTokenRepository.findByUser(theUser);
        return existUser;
    }
}
