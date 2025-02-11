package com.olesya.psyCab.event.listner;

import com.olesya.psyCab.email.SendEmailMessage;
import com.olesya.psyCab.event.RegistrationCompleteEvent;
import com.olesya.psyCab.token.VerificationTokenService;
import com.olesya.psyCab.entity.User;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private User user;
    private final VerificationTokenService service;
    private final SendEmailMessage sendEmailMessage;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1.Get the use
        user = event.getUser();
        // 2. Generate a token for the user
        String vToken = UUID.randomUUID().toString();
        // 3. Save the token for the user
        service.saveVerificationTokenForUser(user, vToken);
        // 4. Build the verification url
        String url = "http://localhost:5678" + "/registration/verifyEmail?token=" + vToken;
        // 5. Send the email to the user
        try {
            sendEmailMessage.sendVerificationEmail(user, url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
