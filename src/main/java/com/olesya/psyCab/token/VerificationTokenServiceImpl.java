package com.olesya.psyCab.token;

import com.olesya.psyCab.entity.PasswordResetToken;
import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService{
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    public String validateToken(String token) {
        Optional<VerificationToken> theToken = tokenRepository.findByToken(token);
        Calendar calendar = Calendar.getInstance();
        if (theToken.isEmpty()) {
            return "INVALID";
        } else if ((theToken.get().getExpirationTime().getTime()-calendar.getTime().getTime()) <= 0){
            return "EXPIRED";
        } else {
            User user = theToken.get().getUser();
            user.setEnabled(true);
            userRepository.save(user);
            return "VALID";
        }
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        VerificationToken newToken = new VerificationToken(token, user);
        tokenRepository.save(newToken);
    }

    @Override
    public void saveNewVerificationTokenForUser(User user, String token){
        String vToken = UUID.randomUUID().toString();
        VerificationToken newToken = new VerificationToken(token, user);
        Optional<VerificationToken> existUser = tokenRepository.findByUser(user);

        if (existUser.isPresent()){
            deleteUserToken(user.getUserId());
            tokenRepository.save(newToken);
        } else {
            tokenRepository.save(newToken);
        }
    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void deleteUserToken(Long userId) {
        tokenRepository.deleteByUserUserId(userId);
    }
}
