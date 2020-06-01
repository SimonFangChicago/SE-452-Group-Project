package com.se452.group5.MediHelp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.se452.group5.MediHelp.entity.Doctor;
import com.se452.group5.MediHelp.entity.Patient;
import com.se452.group5.MediHelp.repository.DoctorRepository;
import com.se452.group5.MediHelp.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/get_doctor_by_id/{id}")
    public String getDoctorByID(@RequestParam Long id) {

        String result = "";
        System.out.println("\n1.findAll()...");
        for (Doctor doctor : doctorRepository.findAll()) {
            // System.out.println(patient);
            if (doctor.getDOCTORID().equals(id)) {
                result = doctor.toString();
            }
        }

        return result.length() > 0 ? result : "doctor not found";
    }

    @GetMapping("/DoctorLogin")
    public String DoctorLogin() {
        return "DoctorLoginScreen";
    }

    @GetMapping("/DoctorPortal")
    public String DoctorPortal(@RequestParam String email, @RequestParam String password, Model model) {
        model.addAttribute("email", email);
        return "DoctorPortal.html";
    }

    
    @RequestMapping(value = "/AddNewPatient", method = RequestMethod.POST)
    public ModelAndView processAddNewPatient(ModelAndView modelAndView,Patient patient,
            BindingResult bindingResult, HttpServletRequest request) {

                patientRepository.save(patient);

                modelAndView.addObject("patientName", patient.getPATIENTNAME());
                modelAndView.setViewName("AddPatientSuccessfully");

                return modelAndView;

    }
    
}