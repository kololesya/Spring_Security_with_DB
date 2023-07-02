package com.olesya.psyCab.controller;

import com.olesya.psyCab.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ReservationController {

    @Autowired
    private SpecialistRepository specialistRepository;

    @GetMapping("/reservation")
    public ModelAndView getAllSpecialists() {
        ModelAndView mav = new ModelAndView("reservation");
        mav.addObject("specialists", specialistRepository.findAll());
        return mav;
    }
}
