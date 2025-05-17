package com.fiuza.appointment_scheduling.infra.adapter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiuza.appointment_scheduling.core.dto.request.UpdateConsultationDTO;

import java.time.LocalDateTime;

public record UpdateConsultation(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dateAndHour
) {
    public UpdateConsultationDTO toCoreDTO() {
        return new UpdateConsultationDTO(this.dateAndHour);
    }
}
