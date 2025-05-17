package com.fiuza.appointment_scheduling.core.entities.consulation;

import com.fiuza.appointment_scheduling.core.exceptions.NullDataNotNullException;

import java.util.Map;

public class ConsultationValidation {
    public static void validation(Consultation consultation) {
        Map<String, Object> fieldsToValidate = Map.of(
                "dateAndHour", consultation.getDateAndHour(),
                "patient", consultation.getPatient(),
                "nurse", consultation.getNurse(),
                "doctor",consultation.getDoctor() ,
                "specialty", consultation.getSpecialty()
        );

        fieldsToValidate.forEach((fieldName, value) -> {
            if (value == null || value == "") {
                throw new NullDataNotNullException(fieldName + " não pode estar em branco ou nulo");
            }
        });
    }
}
