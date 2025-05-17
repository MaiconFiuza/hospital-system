package com.fiuza.appointment_scheduling.core.dto.response;

import java.time.LocalDateTime;

public record Appointment(
        String patientName,
        String doctorName,
        LocalDateTime dateAndHour,
        String reasonForContact
) {
}
