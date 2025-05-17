package com.fiuza.appointment_scheduling.helper.dto;

import com.fiuza.appointment_scheduling.core.dto.response.Appointment;

import java.time.LocalDateTime;

public class AppointmentHelper {

    public static Appointment defaultDTO() {
        return new Appointment(
                "Paciente Teste",
                "Doutor(a) teste",
                LocalDateTime.of(2025,07,03,10, 15),
                "Aviso de marcação de consulta"
        );
    }

    public static Appointment defaultUpdatedDTO() {
        return new Appointment(
                "Paciente Teste",
                "Doutor(a) teste",
                LocalDateTime.of(2025,07,03,10, 15),
                "Aviso de remarcação de consulta"
        );
    }

    public static Appointment defaultDeleteDTO() {
        return new Appointment(
                "Paciente Teste",
                "Doutor(a) teste",
                LocalDateTime.of(2025,07,03,10, 15),
                "Aviso de consulta cancelada, caso haja dúvida por favor entrar em contato"
        );
    }
}
