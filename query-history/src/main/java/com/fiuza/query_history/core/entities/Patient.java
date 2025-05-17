package com.fiuza.query_history.core.entities;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Patient {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthdate;
    private List<Consultation> consultations = Collections.emptyList();

    public Patient(Long id, String name, String cpf, LocalDate birthdate, List<Consultation> consultations) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.consultations = consultations;
    }

    public Patient(String name, String cpf, LocalDate birthdate) {
        this.name = name;
        this.cpf = cpf;
        this.birthdate = birthdate;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
