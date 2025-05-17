package com.fiuza.appointment_scheduling.core.entities.doctor;

import com.fiuza.appointment_scheduling.core.exceptions.NullDataNotNullException;

import java.util.Map;

public class DoctorValidation {
    public static void validation(Doctor doctor) {
        Map<String, Object> fieldsToValidate = Map.of(
                "name", doctor.getName(),
                "crm", doctor.getCrm(),
                "specialty", doctor.getSpecialty()
        );

        fieldsToValidate.forEach((fieldName, value) -> {
            if (value == null || value == "") {
                throw new NullDataNotNullException(fieldName + " não pode estar em branco ou nulo");
            }
        });
    }
}
