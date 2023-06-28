package com.olesya.psyCab.controller;

import com.olesya.psyCab.event.RegistrationCompleteEvent;
import com.olesya.psyCab.request.RegistrationRequest;
import com.olesya.psyCab.request.password.PasswordResetTokenService;
import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.service.UserService;
import com.olesya.psyCab.token.VerificationToken;
import com.olesya.psyCab.token.VerificationTokenServiceImpl;
import com.olesya.psyCab.entity.User;
import com.olesya.psyCab.utility.UrlUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private final VerificationTokenServiceImpl tokenService;
    @Autowired
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    @Autowired
    private final UserRepository userRepository;

    private final PasswordResetTokenService passwordResetTokenService;
    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    @GetMapping("/success")
    public String successRegistration(){
        return "/success";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid RegistrationRequest registration,
                               HttpServletRequest request) {

        if (userRepository.existsByUsername(registration.getUsername())) {
            return "redirect:/registration/registration-form?invalidUsername";
        }

        if (userRepository.existsByEmail(registration.getEmail())) {
            return "redirect:/registration/registration-form?invalidEmail";
        }

        try {
            User user = userService.registerUser(registration);
            publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicationUrl(request)));
            return "redirect:/registration/success?success";
        } catch (ConstraintViolationException e){
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                return "redirect:/registration/registration-form?incorrectEmail";
            }
        }
        return "redirect:/registration/registration-form?incorrectEmail";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@Param("token") String token){
        Optional<VerificationToken> theToken = tokenService.findByToken(token);
        String verificationResult = tokenService.validateToken(token);

        if (theToken.isPresent() && theToken.get().getUser().isEnabled()){
            return "redirect:/login?verified";
        } else if (verificationResult.toLowerCase().equals("invalid")) {
            return "redirect:/error?invalid";
        } else if (verificationResult.toLowerCase().equals("expired")) {
            return "redirect:/error?expired";
        } else {
            return "redirect:/login?verified";
        }
    }
}
