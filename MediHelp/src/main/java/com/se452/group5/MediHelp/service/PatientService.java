package com.se452.group5.MediHelp.service;


import com.se452.group5.MediHelp.entity.AppUser;
import com.se452.group5.MediHelp.entity.Patient;
import com.se452.group5.MediHelp.repository.PatientRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PatientService")
public class PatientService {
	
	private PatientRepository patientRepository;
	
	@Autowired
    public PatientService(PatientRepository patientnRepository) { 
      this.patientRepository = patientRepository;
    }
	
	public void savePatient(Patient patient) {
		patientRepository.save(patient);
	}

	public Optional<Patient> findByID(int id) {
		return patientRepository.findById(id);
	}

}
