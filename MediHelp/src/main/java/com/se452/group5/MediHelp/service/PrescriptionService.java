package com.se452.group5.MediHelp.service;

import com.se452.group5.MediHelp.entity.Prescription;
import com.se452.group5.MediHelp.repository.PrescriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PrescriptionService")
public class PrescriptionService {
	
	private PrescriptionRepository prescriptionRepository;
	
	 @Autowired
	    public PrescriptionService(PrescriptionRepository prescriptionRepository) { 
	      this.prescriptionRepository = prescriptionRepository;
	    }
	 
	 public void savePrescription(Prescription prescription) {
			prescriptionRepository.save(prescription);
		}

}
