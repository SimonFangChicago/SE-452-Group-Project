package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.model.Doctor;
import com.se452.group5.MediHelp.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    
    @GetMapping("/get_doctor_by_id/{id}")
    public String getDoctorByID(@RequestParam Long id) {

        String result = "";
        System.out.println("\n1.findAll()...");
        for (Doctor doctor : doctorRepository.findAll()) {
            //System.out.println(patient);
            if(doctor.getDCOTORID().equals(id))
            {
                result = doctor.toString();
            }
        }

        return result.length()>0?result:"doctor not found";
    }
    
}