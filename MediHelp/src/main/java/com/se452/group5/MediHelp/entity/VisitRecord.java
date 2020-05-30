package com.se452.group5.MediHelp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;


@Entity
@Table(name = "VISITRECORD")
@ToString
public class VisitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    public int PatientID;

    public int DoctorID;

    public String VD;
    
    public String Reason;
    
	public String Result;
}