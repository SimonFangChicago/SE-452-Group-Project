package com.se452.group5.MediHelp.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Prescription")
@ToString
public class Prescription implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PRESCRIPTIONID;
	
	private String PRESCRIPTIONPATIENTNAME;
	
	private String PRESCRIPTIONMEDICATION;

}
