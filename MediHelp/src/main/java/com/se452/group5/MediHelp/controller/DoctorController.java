package com.se452.group5.MediHelp.controller;

import com.se452.group5.MediHelp.entity.Doctor;
import com.se452.group5.MediHelp.entity.Patient;
import com.se452.group5.MediHelp.entity.Prescription;
import com.se452.group5.MediHelp.repository.DoctorRepository;
import com.se452.group5.MediHelp.service.PatientService;
import com.se452.group5.MediHelp.service.PrescriptionService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    private PrescriptionService prescriptionService;
    private PatientService patientService;
    
    public DoctorController(PrescriptionService prescriptionService, PatientService patientService){
    	this.prescriptionService = prescriptionService;
    	this.patientService = patientService;
    	
    }
    
    @GetMapping("/get_doctor_by_id/{id}")
    public String getDoctorByID(@RequestParam Long id) {

        String result = "";
        System.out.println("\n1.findAll()...");
        for (Doctor doctor : doctorRepository.findAll()) {
            //System.out.println(patient);
            if(doctor.getDOCTORID().equals(id))
            {
                result = doctor.toString();
            }
        }

        return result.length()>0?result:"doctor not found";
    }

    

    @GetMapping("/DoctorLogin")
    public String DoctorLogin() {
        return "DoctorLoginScreen";
    }

    @GetMapping("/DoctorPortal")
    public String DoctorPortal(@RequestParam String email, @RequestParam String password,Model model) {
        model.addAttribute("email", email);
        return "DoctorPortal.html";
    }
    
    @RequestMapping(value = "/prescribe", method = RequestMethod.POST)
    public void Prescribe(@Valid Prescription prescription) {
    	prescriptionService.savePrescription(prescription);
    }
    
    @RequestMapping(value ="/addPatient", method = RequestMethod.POST)
    public void addPatient(@Valid Patient patient) {
    	patientService.savePatient(patient);
    }
    
}