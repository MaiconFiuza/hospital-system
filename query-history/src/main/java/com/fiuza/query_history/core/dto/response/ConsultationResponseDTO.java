package com.fiuza.query_history.core.dto.response;

import com.fiuza.query_history.core.enums.Specialty;

import java.time.LocalDateTime;

public record ConsultationResponseDTO(
        Long id,
        ConsultationDoctorResponseDTO doctor,
        ConsultationNurseResponseDTO markedBy,
        ConsultationPatientResponseDTO patient,
        LocalDateTime dateAndHour,
        Specialty specialty
) {
}
