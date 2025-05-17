package com.fiuza.appointment_scheduling.core.entities.doctor;

import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.enums.Specialty;

import java.util.Collections;
import java.util.List;

public class Doctor {
    private Long id;
    private String name;
    private String crm;
    private Specialty specialty;
    private List<Consultation> consultations = Collections.emptyList();

    public Doctor(Long id, String name, String crm, Specialty specialty, List<Consultation> consultations) {
        this.id = id;
        this.name = name;
        this.crm = crm;
        this.specialty = specialty;
        this.consultations = consultations;
        DoctorValidation.validation(this);
    }

    public Doctor(String name, String crm, Specialty specialty) {
        this.name = name;
        this.crm = crm;
        this.specialty = specialty;
        DoctorValidation.validation(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
