package com.fiuza.appointment_scheduling.helper.dto;

import com.fiuza.appointment_scheduling.core.dto.request.UpdateConsultationDTO;

import java.time.LocalDateTime;

public class UpdateConsultationDtoHelper {

    public static UpdateConsultationDTO defaultDTO() {
        return new UpdateConsultationDTO(LocalDateTime.of(2025,07,03,10, 15));
    }
}
