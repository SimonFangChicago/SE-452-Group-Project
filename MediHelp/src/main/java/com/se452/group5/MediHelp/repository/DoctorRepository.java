package com.se452.group5.MediHelp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.se452.group5.MediHelp.entity.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

}