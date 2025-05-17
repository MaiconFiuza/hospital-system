package com.fiuza.appointment_scheduling.core.dto.request;

import java.time.LocalDate;

public record PatientDTO(
        String name,
        String cpf,
        LocalDate birthdate
) {
}
