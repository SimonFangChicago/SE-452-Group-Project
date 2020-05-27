package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.model.Patient;
import com.se452.group5.MediHelp.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    
    @GetMapping("/get_patient_by_id/{id}")
    public String getPatientByID(@PathVariable(required=false,name ="id") String id) {

        String result = "";
        System.out.println("\n1.findAll()...");
        for (Patient patient : patientRepository.findAll()) {
            System.out.println(patient);
            System.out.println(id);
            if(patient.getPATIENTID().toString().equals(id))
            {
                result = patient.toString();
            }
        }

        return result.length()>0?result:"PatientNotFound";
    }

    @GetMapping("/PatientLogin")
    public String PatientLogin() {
        return "PatientLoginScreen";
    }

    @GetMapping("/PatientPortal")
    public String DoctorPortal() {

        return "PatientPortal.html";
    }

    @GetMapping("/test")
    public String hello(Model model){

        model.addAttribute("name","simon");
        return "hello";
    }
    
}