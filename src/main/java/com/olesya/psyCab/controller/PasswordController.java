package com.olesya.psyCab.controller;

import com.olesya.psyCab.email.SendEmailMessage;
import com.olesya.psyCab.registration.password.PasswordResetToken;
import com.olesya.psyCab.registration.password.PasswordResetTokenRepository;
import com.olesya.psyCab.registration.password.PasswordResetTokenService;
import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.service.UserService;
import com.olesya.psyCab.token.VerificationTokenServiceImpl;
import com.olesya.psyCab.user.User;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class PasswordController {
    @Autowired
    private final VerificationTokenServiceImpl tokenService;
    @Autowired
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private SendEmailMessage emailMessage;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    private final PasswordResetTokenService passwordResetTokenService;

    @GetMapping("/forgot-password-request")
    public String forgotPasswordForm(){
        return "forgot-password-form";
    }

    @PostMapping("/forgot-password")
    public String resetPasswordRequest(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        User resetPassUser = userService.findByEmail(email);
        String newToken = UUID.randomUUID().toString();

        if(userRepository.existsByEmail(email)) {
            Set<PasswordResetToken> setTokens = new HashSet<>(passwordResetTokenService.getAllTokens()); //get list of tokens
            Map<Long, String> map = new HashMap<>();
            for (PasswordResetToken existsToken : setTokens) {
                map.put(existsToken.getUser().getUserId(), existsToken.getToken());
            }
            System.out.println(map);
            for (PasswordResetToken existsToken : setTokens){
                if(map.containsKey(resetPassUser.getUserId())){
                    map.replace(resetPassUser.getUserId(), existsToken.getToken(), newToken);
                    passwordResetTokenRepository.save(existsToken);
                } else {
                    System.out.println("No");
                }
            }
            System.out.println(map);
//

//              if (existsToken.getUser().getUserId() == resetPassUser.getUserId()){
//                  System.out.println(existsToken.getToken());
//                  existsToken.setToken(newToken);
//                  System.out.println(existsToken.getToken());
//                  passwordResetTokenRepository.save(existsToken);
//                  System.out.println(existsToken.getToken());
//                } else {
//                  passwordResetTokenService.saveResetTokenForUser(resetPassUser, newToken);
//                }
//            }




                String url = "http://localhost:5678" + "/login/password-reset-form?token=" + newToken;
                try {
                    emailMessage.sendPasswordResetVerificationEmail(resetPassUser, url);
                } catch (MessagingException | UnsupportedEncodingException e) {
                    model.addAttribute("error", e.getMessage());
                }
                return "redirect:/login/forgot-password-request/success?success";

        }
        return "redirect:/login/forgot-password-request?not_found";
    }

    @GetMapping("/forgot-password-request/success")
    public String successRegistration(){
        return "successResetPassword";
    }

    @GetMapping("/password-reset-form")
    public String passwordResetForm(@RequestParam("token") String token, Model model){
        model.addAttribute("token", token);
        return "password-reset-form";
    }

    @PostMapping("/reset-password")
    public String resetPassword(HttpServletRequest request){
        String theToken = request.getParameter("token");
        String password = request.getParameter("password");
        String tokenVerificationResult = passwordResetTokenService.validatePasswordResetToken(theToken);

        if (!tokenVerificationResult.equalsIgnoreCase("valid")){
            return "redirect:/error?invalid_token";
        }

        Optional<User> theUser = passwordResetTokenService.findUserByPasswordResetToken(theToken);
        if(theUser.isPresent()){
            passwordResetTokenService.saveNewPassword(theUser.get(), password);
            return "redirect:/login?reset_success";
        }
        return "redirect:/error?not_found";
    }
}
