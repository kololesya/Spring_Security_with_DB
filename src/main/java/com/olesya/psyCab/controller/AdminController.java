package com.olesya.psyCab.controller;

import com.olesya.psyCab.entity.Specialist;
import com.olesya.psyCab.entity.User;
import com.olesya.psyCab.repository.SpecialistRepository;
import com.olesya.psyCab.repository.UserRepository;
import com.olesya.psyCab.request.RegistrationSpecialistRequest;
import com.olesya.psyCab.service.SpecialistService;
import com.olesya.psyCab.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final UserRepository uRepo;

    @Autowired
    private final UserService uService;

    @Autowired
    private final SpecialistRepository specRepo;

    @Autowired
    private final SpecialistService specService;

    @GetMapping("/all-users")
    public ModelAndView getListRegisteredUsers(Model model){
        ModelAndView mav = new ModelAndView("adminUsers");
        mav.addObject("user", uRepo.findAll());
        return mav;
    }

    @GetMapping("/all-users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId){
        uService.deleteUser(userId);
        return "redirect:/admin/all-users?delete_success";
    }

//    @GetMapping("/all-users/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") Long userId, Model model) {
//        User user = uRepo.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
//
//        model.addAttribute("user", user);
//        return "update-user";
//    }
//
//    @PostMapping("/all-users/edit/{id}")
//    public String updateUser(@PathVariable("id") Long userId, @Valid User user,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setUserId(userId);
//            return "update-user";
//        }
//
//        uRepo.save(user);
//        return "redirect:/admin/all-users/update?success";
//    }

//    @GetMapping("/all-users/edit/{id}")
//    public String showUpdateForm(@PathVariable("email") Long id, Model model){
//        Optional<User> user = uService.findById(id);
//        model.addAttribute("user", user.get());
//        return "update-user";
//    }

//    @PostMapping("/all-users/update/{id}")
//    public String updateUser(@PathVariable("id") Long id, User user){
//        uService.updateUser(id, user.getUsername(), user.getEmail());
//        return "redirect:/admin/all-users/update?success";
//    }

    @GetMapping("/all-specialists")
    public ModelAndView getListSpecialists(Model model){
        ModelAndView mav = new ModelAndView("adminSpecialists");
        mav.addObject("specialists", specRepo.findAll());
        return mav;
    }

    @GetMapping("/all-specialists/registration-form")
    public String showRegistrationForm(Model model){
        model.addAttribute("specialists", new RegistrationSpecialistRequest());
        return "registrationSpecialistForm";
    }
//
//    @DeleteMapping
//    public void deleteSpecialist(){
//        specRepo.findByLastName()
//    }

    @PostMapping("/all-specialists/registerSpecialist")
    public String registerSpecialist(@ModelAttribute("specialists") @Valid RegistrationSpecialistRequest registration,
                                     HttpServletRequest request, RegistrationSpecialistRequest regRequest) {

        if (specRepo.existsByFirstName(registration.getFirstName())
                && specRepo.existsByLastName(registration.getLastName())
                && specRepo.existsBySpecialization(registration.getSpecialization())) {
            return "redirect:/admin/all-specialists/registration-form?invalidSpecialist";
        }
        Specialist spec = specService.registerSpecialist(registration);
        return "redirect:/admin/all-specialists";
    }

    @GetMapping("/all-reservation")
    public ModelAndView getListReservations(Model model){
        ModelAndView mav = new ModelAndView("adminReservation");
        mav.addObject("specialists", specRepo.findAll());
        return mav;
    }
}
