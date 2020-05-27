package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.model.Doctor;
import com.se452.group5.MediHelp.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private DoctorRepository doctorRepository;
    
    @GetMapping("/")
    public String Index() {

        return "index";
    }
    
}