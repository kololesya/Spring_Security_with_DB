package com.olesya.psyCab.controller;

import com.olesya.psyCab.entity.User;
import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/my-account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final UserRepository uRepo;

    @Autowired
    private final UserService uService;

    @GetMapping("/update-password")
    public String getFormUpdatePassword(){
        return "update-password";
    }

    @PostMapping("/update-password/{id}")
    public String updatePassword(@PathVariable("id") Long userId, HttpServletRequest request){
        String password = request.getParameter("password");
        User user = uRepo.findById(userId).get();

        if (!uService.checkIfValidOldPassword(user, request.getParameter("oldPassword"))) {
            return "redirect:/my-account/update-password?error";
        }

        uService.changeUserPassword(user, password);

        return "redirect:/my-account?successUpdatePassword";
    }

    @PostMapping("/updateEmail/{id}")
    public String updateEmail(@RequestParam String email, @PathVariable("id") Long userId){
        User user = uRepo.findById(userId).get();
        if (uRepo.existsByEmail(email)){
            return "redirect:/my-account?errorUpdateEmail";
        } else {
        uService.changeEmail(user, email);}
        return "redirect:/my-account?successUpdateEmail";
    }

    @PostMapping("/updateUsername/{id}")
    public String updateUsername(@RequestParam String newUsername, @PathVariable("id") Long userId){
        User user = uRepo.findById(userId).get();
        if (uRepo.existsByUsername(newUsername)){
            return "redirect:/my-account?errorUpdateUsername";
        } else {
            uService.changeUsername(user, newUsername);}
        return "redirect:/my-account?successUpdateUsername";
    }

    @PostMapping("/updateName/{id}")
    public String updateName(@RequestParam String newName, @PathVariable("id") Long userId){
        User user = uRepo.findById(userId).get();
        try {
            uService.changeFirstName(user, newName);
            return "redirect:/my-account?successUpdateName";
        } catch (Exception ex) {
            return "redirect:/my-account?errorUpdateUserInfo";
        }
    }

    @PostMapping("/updateLastName/{id}")
    public String updateLastName(@RequestParam String newLastName, @PathVariable("id") Long userId){
        User user = uRepo.findById(userId).get();
        try {
            uService.changeLastName(user, newLastName);
            return "redirect:/my-account?successUpdateName";
        } catch (Exception ex) {
            return "redirect:/my-account?errorUpdateUserInfo";
        }
    }

    @PostMapping("/updatePhoneNumber/{id}")
    public String updateTelephoneNumber(@RequestParam Long newNumber, @PathVariable("id") Long userId){
        User user = uRepo.findById(userId).get();
        try {
            uService.changePhoneNumber(user, newNumber);
            return "redirect:/my-account?successUpdatePhoneNumber";
        } catch (Exception ex) {
            return "redirect:/my-account?errorUpdateUserInfo";
        }
    }

}
