package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.model.Patient;
import com.se452.group5.MediHelp.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    
    @GetMapping("/get_patient_by_id")
    public String getPatientByID(@RequestParam Long id) {

        String result = "";
        System.out.println("\n1.findAll()...");
        for (Patient patient : patientRepository.findAll()) {
            //System.out.println(patient);
            if(patient.getPATIENTID().equals(id))
            {
                result = patient.toString();
            }
        }

        return result.length()>0?result:"patient not found";
    }

    @GetMapping("/test")
    public String hello(Model model){

        model.addAttribute("name","simon");
        return "hello";
    }
    
}