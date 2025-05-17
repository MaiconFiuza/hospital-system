package com.fiuza.appointment_scheduling.infra.adapter.dto;

import com.fiuza.appointment_scheduling.core.dto.request.ConsultationDTO;
import com.fiuza.appointment_scheduling.core.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ConsultationRequest(
        String cip,
        String cpf,
        String crm,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dateAndHour,
        Specialty specialty
) {
    public ConsultationDTO toCoreDTO() {
        return new ConsultationDTO(this.cip, this.cpf(), this.crm, this.dateAndHour, this.specialty);
    }

}
