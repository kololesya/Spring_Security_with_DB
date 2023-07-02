package com.olesya.psyCab.controller;

import com.olesya.psyCab.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String accountPage(){
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
}
