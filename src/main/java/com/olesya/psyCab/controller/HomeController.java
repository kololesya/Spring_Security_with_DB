package com.olesya.psyCab.controller;

import com.olesya.psyCab.entity.User;
import com.olesya.psyCab.security.ProjectUserDetails;
import com.olesya.psyCab.service.UserService;
import com.olesya.psyCab.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private final UserServiceImpl uService;

    public HomeController(UserServiceImpl uService) {
        this.uService = uService;
    }

    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping("/specialists")
    public String doctorsPage() {return "specialists";}

    @GetMapping("/error")
    public String errorPage() {return "error";}

    @GetMapping("/myVisit")
    public String visitPage(){
        return "myVisit";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/my-account")
    public String accountPage(HttpServletRequest request){
        return "my-account";
    }

}
