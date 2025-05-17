package com.example.shared.entities.helper;

import com.example.shared.entities.PatientModel;

import java.time.LocalDate;
import java.util.Collections;

public class PatientModelHelper {

    public static PatientModel createPatientDefault() {
        return new PatientModel(
                1L,
                "Paciente Teste",
                LocalDate.of(1995, 12, 7),
                "99999999999",
                Collections.emptyList()
        );
    }
}
