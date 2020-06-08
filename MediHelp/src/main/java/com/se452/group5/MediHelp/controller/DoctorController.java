package com.se452.group5.MediHelp.controller;

import java.security.Principal;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.se452.group5.MediHelp.entity.Doctor;
import com.se452.group5.MediHelp.entity.Patient;
import com.se452.group5.MediHelp.entity.Prescription;
import com.se452.group5.MediHelp.repository.DoctorRepository;
import com.se452.group5.MediHelp.repository.PatientRepository;
import com.se452.group5.MediHelp.repository.PrescriptionRepository;

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

    @Autowired
    private PrescriptionRepository prescriptionRepository;

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
            BindingResult bindingResult, HttpServletRequest request,Principal principal) {

                patient.setDOCTORNAME(principal.getName());
                patientRepository.save(patient);

                modelAndView.addObject("patientName", patient.getPATIENTNAME());
                modelAndView.setViewName("AddPatientSuccessfully");

                return modelAndView;

    }


    @RequestMapping(value = "/UpdatePatient", method = RequestMethod.GET)
    public ModelAndView EditPatient(ModelAndView modelAndView,@RequestParam("patientName") String patientName,Principal principal) {
                Iterator<Patient> patientIterator = patientRepository.findAll().iterator();
                Patient pInDB = null;
                Patient model = null;
                while (patientIterator.hasNext()) {
                    pInDB = patientIterator.next();
                    if(pInDB.getPATIENTNAME().equals(patientName))
                    {    
                        model = pInDB;
                        break;
                    }
                }

                modelAndView.setViewName("/EditPatient");
                modelAndView.addObject("patient", model);
                return modelAndView;
    }

    @RequestMapping(value = "/PostUpdatePatient", method = RequestMethod.POST)
    public String processUpdatePatient(Patient patient,
            BindingResult bindingResult, HttpServletRequest request,Principal principal) {
                Iterator<Patient> patientIterator = patientRepository.findAll().iterator();
                Patient pInDB = null;
                while (patientIterator.hasNext()) {
                    pInDB = patientIterator.next();
                    if(pInDB.getPATIENTNAME().equals(patient.getPATIENTNAME()))
                    {    
                        pInDB.setPATIENTDOB(patient.getPATIENTDOB());
                        pInDB.setPATIENTNOTES(patient.getPATIENTNOTES());
                        patientRepository.save(pInDB);
                        break;
                    }
                }
                return "UpdateResult";
    }


    @RequestMapping(value = "/DeletePatient", method = RequestMethod.GET)
    public ModelAndView processDeletePatient(ModelAndView modelAndView, @RequestParam("patientName") String patientName,Principal principal) {

                Iterator<Patient> patientIterator = patientRepository.findAll().iterator();
                Patient patient = null;
                while (patientIterator.hasNext()) {
                    patient = patientIterator.next();
                    if(patient.getPATIENTNAME().equals(patientName))
                    {    
                        patientRepository.delete(patient);
                        break;
                    }
                }

                modelAndView.setViewName("redirect:/userinfo");

                return modelAndView;
    }

    @RequestMapping(value = "/DeletePrescription", method = RequestMethod.GET)
    public ModelAndView processDeletePrescription(ModelAndView modelAndView, @RequestParam("pID") String pID,Principal principal) {

                Iterator<Prescription> pIterator = prescriptionRepository.findAll().iterator();
                Prescription prescription = null;
                while (pIterator.hasNext()) {
                    prescription = pIterator.next();
                    if(prescription.getPRESCRIPTIONID().toString().equals(pID))
                    {    
                        prescriptionRepository.delete(prescription);
                        break;
                    }
                }

                modelAndView.setViewName("DeleteResult");

                return modelAndView;
    }

    @RequestMapping(value = "/AddPrescription", method = RequestMethod.POST)
    public ModelAndView processAddPrescription(ModelAndView modelAndView,Prescription prescription,
            BindingResult bindingResult, HttpServletRequest request,Principal principal) {


                String doctorName = principal.getName();

                prescription.setDOCTORNAME(doctorName);
                prescriptionRepository.save(prescription);

                modelAndView.addObject("patientName", prescription.getPATIENTNAME());
                modelAndView.setViewName("AddPrescriptionSuccessfully");

                return modelAndView;

    }

    @RequestMapping(value = "/UpdatePrescription", method = RequestMethod.GET)
    public ModelAndView EditPrescription(ModelAndView modelAndView,@RequestParam("pID") String pID,Principal principal) {
                Iterator<Prescription> pIterator = prescriptionRepository.findAll().iterator();
                Prescription prescription = null;
                Prescription model = null;
                while (pIterator.hasNext()) {
                    prescription = pIterator.next();
                    String pIDStr = prescription.getPRESCRIPTIONID().toString();
                    if(pIDStr.equals(pID))
                    {    
                        model = prescription;
                        break;
                    }
                }

                modelAndView.setViewName("/EditPrescription");
                modelAndView.addObject("prescription", model);
                return modelAndView;
    }

    @RequestMapping(value = "/PostUpdatePrescription", method = RequestMethod.POST)
    public String processUpdatePrescription(Prescription prescription,
            BindingResult bindingResult, HttpServletRequest request,Principal principal) {
                Iterator<Prescription> pIterator = prescriptionRepository.findAll().iterator();
                Prescription p = null;
                while (pIterator.hasNext()) {
                    p = pIterator.next();
                    if(p.getPRESCRIPTIONID().toString().equals(prescription.getPRESCRIPTIONID().toString()))
                    {    
                        p.setMEDICATION(prescription.getMEDICATION());
                        prescriptionRepository.save(p);
                        break;
                    }
                }
                return "UpdateResult";
    }
    
}