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




}
