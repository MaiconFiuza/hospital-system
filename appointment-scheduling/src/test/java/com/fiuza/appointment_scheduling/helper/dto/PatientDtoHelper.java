package com.fiuza.appointment_scheduling.helper.dto;

import com.fiuza.appointment_scheduling.core.dto.request.PatientDTO;

import java.time.LocalDate;

public class PatientDtoHelper {

    public static PatientDTO defaultDto() {
        return new PatientDTO(
                "Paciente Teste",
                "99999999999",
                LocalDate.of(1995, 12, 7)
        );
    }

    public static PatientDTO withNull() {
        return new PatientDTO(
                "Paciente Teste",
                null,
                LocalDate.of(1995, 12, 7)
        );
    }
}
