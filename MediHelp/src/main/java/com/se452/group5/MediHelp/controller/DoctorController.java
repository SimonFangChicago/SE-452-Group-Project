package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.model.Doctor;
import com.se452.group5.MediHelp.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    
    @GetMapping("/get_doctor_by_id")
    public String getDoctorByID(){

        String result = "";
        System.out.println("\n1.findAll()...");
        for (Doctor doctor : doctorRepository.findAll()) {
            System.out.println(doctor);
            result = doctor.toString();
        }

        return result;
    }
    
}