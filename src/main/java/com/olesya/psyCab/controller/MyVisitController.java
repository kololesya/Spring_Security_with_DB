package com.olesya.psyCab.controller;

import com.olesya.psyCab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myVisit")
public class MyVisitController {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    BookingsRepository bookingsRepository;
}
