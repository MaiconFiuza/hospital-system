package com.fiuza.appointment_scheduling.helper.dto;

import com.fiuza.appointment_scheduling.core.dto.request.ConsultationDTO;
import com.fiuza.appointment_scheduling.core.enums.Specialty;

import java.time.LocalDateTime;

public class ConsultationDtoHelper {

    public static ConsultationDTO defaultDTO() {
        return new ConsultationDTO(
                "COREN-SP-123456",
                "99999999",
                "CRM/SP 123456",
                LocalDateTime.of(2025,07,03,10, 15),
                Specialty.CLINICO_GERAL
        );
    }

    public static ConsultationDTO incorrectCpfDTO() {
        return new ConsultationDTO(
                "COREN-SP-123456",
                "11111",
                "CRM/SP 123456",
                LocalDateTime.of(2025,07,03,10, 15),
                Specialty.CLINICO_GERAL
        );
    }

    public static ConsultationDTO withoutCpfDTO() {
        return new ConsultationDTO(
                "COREN-SP-123456",
                "",
                "CRM/SP 123456",
                LocalDateTime.of(2025,07,03,10, 15),
                Specialty.CLINICO_GERAL
        );
    }
}