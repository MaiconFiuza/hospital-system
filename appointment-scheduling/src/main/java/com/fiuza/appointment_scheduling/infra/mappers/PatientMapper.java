package com.fiuza.appointment_scheduling.infra.mappers;

import com.example.shared.entities.PatientModel;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;

import java.util.Collections;

public class PatientMapper {

    public static PatientModel patientToPatientModel(Patient patient) {
        return new PatientModel(
                patient.getId(),
                patient.getName(),
                patient.getBirthdate(),
                patient.getCpf(),
                Collections.emptyList()
        );
    }

    public static Patient patientModelToPatient(PatientModel patientModel) {
        return new Patient(
                patientModel.getId(),
                patientModel.getName(),
                patientModel.getCpf(),
                patientModel.getBirthdate(),
                Collections.emptyList()
        );
    }
}
