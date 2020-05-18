package com.se452.group5.MediHelp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.se452.group5.MediHelp.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

}