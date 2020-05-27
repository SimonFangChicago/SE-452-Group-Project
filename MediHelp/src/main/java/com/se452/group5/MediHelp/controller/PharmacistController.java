package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.model.Doctor;
import com.se452.group5.MediHelp.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String DoctorPortal() {

        return "PharmacistPortal.html";
    }
    
}