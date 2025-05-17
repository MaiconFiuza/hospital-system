package com.fiuza.appointment_scheduling.core.dto.request;

import com.fiuza.appointment_scheduling.core.enums.Specialty;

import java.time.LocalDateTime;

public record ConsultationDTO(
        String cip,
        String cpf,
        String crm,
        LocalDateTime dateAndHour,
        Specialty specialty
) {
}
