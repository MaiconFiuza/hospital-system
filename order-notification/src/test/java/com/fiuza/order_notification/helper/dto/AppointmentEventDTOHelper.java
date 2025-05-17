package com.fiuza.order_notification.helper.dto;

import com.example.shared.entities.dto.AppointmentEventDTO;

import java.time.LocalDateTime;

public class AppointmentEventDTOHelper {
    public static AppointmentEventDTO create() {
        return new AppointmentEventDTO(
                "Teste Paciente",
                "Doutor(a)",
                LocalDateTime.of(2025,07,03,10, 15),
                "Aviso de marcação de consulta"
        );
    }

    public static AppointmentEventDTO canceled() {
        return new AppointmentEventDTO(
                "Teste Paciente",
                "Doutor(a)",
                LocalDateTime.of(2025,07,03,10, 15),
                "Aviso de consulta cancelada, caso haja dúvida por favor entrar em contato"
        );
    }

    public static AppointmentEventDTO update() {
        return new AppointmentEventDTO(
                "Teste Paciente",
                "Doutor(a)",
                LocalDateTime.of(2025,07,03,10, 15),
                "Aviso de remarcação de consulta"
        );
    }
}
