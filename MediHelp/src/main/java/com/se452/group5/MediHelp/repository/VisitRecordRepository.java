package com.se452.group5.MediHelp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.se452.group5.MediHelp.entity.VisitRecord;

@Repository
public interface VisitRecordRepository extends CrudRepository<VisitRecord, Integer> {
   
}