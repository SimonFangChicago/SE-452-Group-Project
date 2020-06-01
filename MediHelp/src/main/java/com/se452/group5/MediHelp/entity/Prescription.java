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
@Table(name = "Prescription")
@ToString
public class Prescription implements Serializable {
    private static final long serialVersionUID = 1L;
        
    @Id
    @SequenceGenerator(name="prescription_id_generator", sequenceName = "RCHUNDRU.prescription_id_sequence",allocationSize = 1)
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator  = "prescription_id_generator") 
    @Column(name = "PrescriptionID", nullable = false)
    private Long PRESCRIPTIONID;

    private String PATIENTNAME;

    private String MEDICATION;

    private String DOCTORNAME;
}