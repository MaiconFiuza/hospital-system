package com.fiuza.appointment_scheduling.core.entities.nurse;

import com.fiuza.appointment_scheduling.core.exceptions.NullDataNotNullException;

import java.util.Map;

public class NurseValidation {
    public static void validation(Nurse nurse) {
        Map<String, Object> fieldsToValidate = Map.of(
                "name", nurse.getName(),
                "cip", nurse.getCip()
        );

        fieldsToValidate.forEach((fieldName, value) -> {
            if (value == null || value == "") {
                throw new NullDataNotNullException(fieldName + " não pode estar em branco ou nulo");
            }
        });
    }
}
