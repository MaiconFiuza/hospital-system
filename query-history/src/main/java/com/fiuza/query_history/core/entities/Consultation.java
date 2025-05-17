package com.fiuza.query_history.core.entities;

import com.fiuza.query_history.core.enums.Specialty;

import java.time.LocalDateTime;

public class Consultation {
    private Long id;
    private LocalDateTime dateAndHour;
    private Patient patient;
    private Nurse nurse;
    private Doctor doctor;
    private Specialty specialty;

    public Consultation(
            Long id,
            LocalDateTime dateAndHour,
            Patient patient,
            Nurse nurse,
            Doctor doctor,
            Specialty specialty
    ) {
        this.id = id;
        this.dateAndHour = dateAndHour;
        this.patient = patient;
        this.nurse = nurse;
        this.doctor = doctor;
        this.specialty = specialty;
    }

    public Consultation(LocalDateTime dateAndHour, Patient patient, Nurse nurse, Doctor doctor, Specialty specialty) {
        this.dateAndHour = dateAndHour;
        this.patient = patient;
        this.nurse = nurse;
        this.doctor = doctor;
        this.specialty = specialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndHour() {
        return dateAndHour;
    }

    public void setDateAndHour(LocalDateTime dateAndHour) {
        this.dateAndHour = dateAndHour;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
