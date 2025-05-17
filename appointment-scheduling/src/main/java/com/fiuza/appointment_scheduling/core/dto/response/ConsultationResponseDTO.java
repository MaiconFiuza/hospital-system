package com.fiuza.appointment_scheduling.core.dto.response;

import com.fiuza.appointment_scheduling.core.enums.Specialty;

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
