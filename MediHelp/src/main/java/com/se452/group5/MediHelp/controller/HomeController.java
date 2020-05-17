package com.se452.group5.MediHelp.controller;

import javax.sql.DataSource;

import com.se452.group5.MediHelp.model.Patient;
import com.se452.group5.MediHelp.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

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
        //return result;
    }
    
}