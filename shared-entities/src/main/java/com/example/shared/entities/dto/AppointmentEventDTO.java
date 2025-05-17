package com.example.shared.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AppointmentEventDTO(
        String patientName,
        String doctorName,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dateAndHour,
        String reasonForContact
) {
}
