package com.olesya.psyCab.email;

import com.olesya.psyCab.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static com.olesya.psyCab.email.ProjectEmailSender.emailMessage;


@Component
public class SendEmailMessage {
    public void sendVerificationEmail(User user, String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Verification Service";
        String mailContent = "<p> Hi, " + user.getUsername() + ", </p>" +
                "<p> Thank you for registering with us,</p>" +
                "" + " <a href =\"" + url + "\"> Verify your email to activate your account </a>" +
                "<p> Thank you <br> User Registration Portal Service</p>";
        emailMessage(subject, senderName, mailContent, user);
    }

    public void sendPasswordResetVerificationEmail(User user, String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "User Verification Service";
        String mailContent = "<p> Hi, " + user.getUsername() + ". </p>" +
                "<p> You recently requested to reset your password. " +
                "Please follow the link below to complete the action </p>" +
                " <a href =\"" + url + "\">Reset password</a>" +
                "<p> User Registration Portal Service</p>";
        emailMessage(subject, senderName, mailContent, user);
    }
}
