package com.fiuza.appointment_scheduling.core.gateway;

import com.fiuza.appointment_scheduling.core.entities.patient.Patient;

import java.util.Optional;

public interface PatientGateway {
    Patient save(Patient patient);

    Optional<Patient> findPatientById(Long id);

    Optional<Patient> findPatientByCpf(String cpf);

    boolean findPatientByCpfValidation(String cpf);
}
