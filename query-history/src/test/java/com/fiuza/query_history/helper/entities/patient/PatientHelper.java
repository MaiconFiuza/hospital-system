package com.fiuza.query_history.helper.entities.patient;

import com.fiuza.query_history.core.entities.Patient;

import java.time.LocalDate;
import java.util.Collections;

public class PatientHelper {

    public static Patient createPatientDefault() {
        return new Patient(
                "Paciente Teste",
                "99999999999",
                LocalDate.of(1995, 12, 7)
        );
    }

    public static Patient createPatientWithId() {
        return new Patient(
                1L,
                "Paciente Teste",
                "99999999999",
                LocalDate.of(1995, 12, 7),
                Collections.emptyList()
        );
    }
}
