package com.olesya.psyCab.token;

import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

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
    public Optional<VerificationToken> findByToken(String token) {

        return tokenRepository.findByToken(token);
    }
}
