package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.model.Patient;
import com.se452.group5.MediHelp.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    
    @GetMapping("/get_patient_by_id")
    public String getPatientByID(){

        String result = "";
        System.out.println("\n1.findAll()...");
        for (Patient patient : patientRepository.findAll()) {
            System.out.println(patient);
            result = patient.toString();
        }

        return result;
    }

    @GetMapping("/test")
    public String hello(Model model){

        model.addAttribute("name","simon");
        return "hello";
    }
    
}