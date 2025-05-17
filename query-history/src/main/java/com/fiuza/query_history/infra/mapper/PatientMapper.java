package com.fiuza.query_history.infra.mapper;

import com.example.shared.entities.PatientModel;
import com.fiuza.query_history.core.entities.Patient;

import java.util.Collections;

public class PatientMapper {
    public static Patient patientModelToPatient(PatientModel patientModel) {
        //mesma coisa sobre a list
        return new Patient(
                patientModel.getId(),
                patientModel.getName(),
                patientModel.getCpf(),
                patientModel.getBirthdate(),
                Collections.emptyList()
        );
    }
}
