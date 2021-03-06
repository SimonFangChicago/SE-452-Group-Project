package com.se452.group5.MediHelp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PharmacistController {


    @GetMapping("/PharmacistLogin")
    public String PatientLogin() {
        return "PharmacistLoginScreen";
    }

    @GetMapping("/PharmacistPortal")
    public String DoctorPortal(@RequestParam String email, @RequestParam String password,Model model) {
        model.addAttribute("email", email);
        return "PharmacistPortal.html";
    }
    
}