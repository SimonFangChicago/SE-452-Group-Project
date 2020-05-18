package com.se452.group5.MediHelp.model;

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
@Table(name = "Doctor")
@ToString
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DCOTORID;

    @Column(name = "PATIENTNAME")
    private String DCOTORNAME;

    private String DOCTORSPECIALTY;

    private String DOCTORSOFFICENAME;
}