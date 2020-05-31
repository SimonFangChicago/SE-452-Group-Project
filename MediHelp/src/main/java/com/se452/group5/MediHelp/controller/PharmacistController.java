package com.se452.group5.MediHelp.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.se452.group5.MediHelp.entity.Patient;
import com.se452.group5.MediHelp.entity.Prescription;
import com.se452.group5.MediHelp.service.PatientService;

@Controller
public class PharmacistController {
	
	PatientService patientService ;
	
	public PharmacistController(PatientService patientService) {
		this.patientService = patientService;
	}


    @GetMapping("/PharmacistLogin")
    public String PatientLogin() {
        return "PharmacistLoginScreen";
    }

    @GetMapping("/PharmacistPortal")
    public String DoctorPortal(@RequestParam String email, @RequestParam String password,Model model) {
        model.addAttribute("email", email);
        return "PharmacistPortal.html";
    }
    
    @RequestMapping(value = "/addPatientNotes", method = RequestMethod.PUT)
    public void addPatientNotes(@Valid Patient patient) {
    	Optional<Patient> patientExists = patientService.findByID( patient.getPATIENTID());
    	if(patientExists!=null) {
    		patientService.savePatient(patient);
    	}
    }
    
}