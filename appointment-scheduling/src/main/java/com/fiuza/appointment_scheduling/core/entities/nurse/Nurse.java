package com.fiuza.appointment_scheduling.core.entities.nurse;

import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;

import java.util.Collections;
import java.util.List;

public class Nurse {
    private Long id;
    private String name;
    private String cip;
    private List<Consultation> consultations = Collections.emptyList();

    public Nurse(Long id, String name, String cip, List<Consultation> consultations) {
        this.id = id;
        this.name = name;
        this.cip = cip;
        this.consultations = consultations;
        NurseValidation.validation(this);
    }

    public Nurse(String name, String cip) {
        this.name = name;
        this.cip = cip;
        NurseValidation.validation(this);
    }

    public Long getId() { return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
