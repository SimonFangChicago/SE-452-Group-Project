package com.se452.group5.MediHelp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Doctor")
@ToString
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
        
    @Id
    @SequenceGenerator(name="doctor_id_generator", sequenceName = "RCHUNDRU.doctor_id_sequence",allocationSize = 1)
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator  = "doctor_id_generator") 
    private Long DOCTORID;

    private String DOCTORNAME;

    private String DOCTORSPECIALTY;

    private String DOCTORSOFFICENAME;
}