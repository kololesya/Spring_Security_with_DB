package com.olesya.psyCab.controller;

import com.olesya.psyCab.entity.Specialist;
import com.olesya.psyCab.repository.SpecialistRepository;
import com.olesya.psyCab.request.RegistrationSpecialistRequest;
import com.olesya.psyCab.service.SpecialistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/specialists")
@RequiredArgsConstructor
public class SpecialistController {

    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired
    private SpecialistService specialistService;

    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model){
        model.addAttribute("specialist", new RegistrationSpecialistRequest());
        return "registrationSpecialistForm";
    }

    @PostMapping("/registerSpecialist")
    public String registerSpecialist(@ModelAttribute("specialist") @Valid RegistrationSpecialistRequest registration,
                                     HttpServletRequest request, RegistrationSpecialistRequest regRequest) {
//        Specialist spec = new Specialist(
//                regRequest.getFirstName(),
//                regRequest.getLastName(),
//                regRequest.getSpecialization());
//
//        specialistRepository.save(spec);



//        if (specialistRepository.(registration.getUsername())) {
//            return "redirect:/registration/registration-form?invalidUsername";
//        }
//
//        if (specialistRepository.existsByEmail(registration.getEmail())) {
//            return "redirect:/registration/registration-form?invalidEmail";
//        }

        Specialist spec = specialistService.registerSpecialist(registration);
        return "redirect:/specialists";


    }

}
