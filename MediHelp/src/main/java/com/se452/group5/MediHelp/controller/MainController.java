package com.se452.group5.MediHelp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.se452.group5.MediHelp.utils.WebUtils;
import com.se452.group5.MediHelp.entity.Doctor;
import com.se452.group5.MediHelp.entity.Patient;
import com.se452.group5.MediHelp.entity.Prescription;
import com.se452.group5.MediHelp.entity.VisitRecord;
import com.se452.group5.MediHelp.repository.AppUserRepository;
import com.se452.group5.MediHelp.repository.DoctorRepository;
import com.se452.group5.MediHelp.repository.PatientRepository;
import com.se452.group5.MediHelp.repository.PrescriptionRepository;
import com.se452.group5.MediHelp.repository.UserRoleRepository;
import com.se452.group5.MediHelp.repository.VisitRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class MainController {

    @Autowired
    private VisitRecordRepository visitRecordRep;

    @Autowired
    private PatientRepository patientRep;

    @Autowired
    private AppUserRepository appUserRep;

    @Autowired
    private DoctorRepository doctorRep;

    @Autowired
    private PrescriptionRepository prescriptionRep;
 
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "login";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        if(userInfo.contains("ROLE_ADMIN"))
        {
            return "adminPage";
        }

        if(userInfo.contains("ROLE_DOCTOR"))
        {
            model.addAttribute("doctorName", principal.getName());

            Iterator<Patient> patientIterator = patientRep.findAll().iterator();
            List<Patient> patients = new ArrayList<Patient>();
            Patient curP = null;
            while (patientIterator.hasNext()) {
                curP = patientIterator.next();
                if(curP.getDOCTORNAME().equals(principal.getName()))
                {
                    patients.add(curP);
                   
                }
            }

            model.addAttribute("Patients", patients);

            ////////////////////////////////////////////////////////////

            Iterator<Prescription> prescriptionIterator = prescriptionRep.findAll().iterator();
            List<Prescription> prescriptions = new ArrayList<Prescription>();
            Prescription curPrescription = null;
            while (prescriptionIterator.hasNext()) {
                curPrescription = prescriptionIterator.next();
                if(curPrescription.getDOCTORNAME().equals(principal.getName()))
                {
                    prescriptions.add(curPrescription);
                   
                }
            }

            model.addAttribute("prescriptions", prescriptions);

            return "DoctorPortal";
        }

        if(userInfo.contains("ROLE_PHARMACIST"))
        {
            return "PHARMACISTPortal";
        }

        if(userInfo.contains("ROLE_PATIENT"))
        {
            Iterable<VisitRecord> visitRecordsIterable = visitRecordRep.findAll();

            List<VisitRecord> recordList = new ArrayList<VisitRecord>();
            Iterator<VisitRecord> iterator = visitRecordsIterable.iterator();
            while (iterator.hasNext()) {
                recordList.add(iterator.next());
            }

            model.addAttribute("VisitRecord", recordList);

            int userId = appUserRep.findByUserName(userName).getUserId().intValue();
            Iterator<Patient> patientIterator = patientRep.findAll().iterator();
            Patient curP = null;
            while (patientIterator.hasNext()) {
                curP = patientIterator.next();
                if(curP.getUser_ID()!=null && curP.getUser_ID().intValue() == userId)
                {
                    model.addAttribute("PatientInfo", curP);
                }
            }

            if(curP!=null && curP.getDOCTORNAME()!=null)
            {
                Doctor curD = null;
                Iterator<Doctor> doctorIterator = doctorRep.findAll().iterator();
                while (doctorIterator.hasNext()) {
                    curD = doctorIterator.next();
                    String pDname = curP.getDOCTORNAME();
                    String dName = curD.getDOCTORNAME();
                    if(pDname.equals(dName))
                    {
                        model.addAttribute("PatientDoctor", curD);
                    }
                }

                Iterator<Prescription> prescriptionIterator = prescriptionRep.findAll().iterator();
                List<Prescription> prescriptions = new ArrayList<Prescription>();
                Prescription curPrescription = null;
                while (prescriptionIterator.hasNext()) {
                    curPrescription = prescriptionIterator.next();
                    if(curPrescription.getPATIENTNAME().equals(curP.getPATIENTNAME()))
                    {
                        prescriptions.add(curPrescription);
                    
                    }
                }

                model.addAttribute("prescriptions", prescriptions);
            }

            return "PatientPortal";
        }

        return "403Page";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
 
}