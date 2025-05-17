package com.fiuza.appointment_scheduling.core.entities.patient;

import com.fiuza.appointment_scheduling.core.exceptions.NullDataNotNullException;

import java.util.Map;

public class PatientValidation {

    public static void validation(Patient patient) {
        Map<String, Object> fieldsToValidate = Map.of(
                "name", patient.getName(),
                "cpf", patient.getCpf(),
                "birthdate", patient.getBirthdate()
        );

        fieldsToValidate.forEach((fieldName, value) -> {
            if (value == null || value == "") {
                throw new NullDataNotNullException(fieldName + " não pode estar em branco ou nulo");
            }
        });
    }
}
