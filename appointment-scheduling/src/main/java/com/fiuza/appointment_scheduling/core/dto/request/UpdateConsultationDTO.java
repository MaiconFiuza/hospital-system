package com.fiuza.appointment_scheduling.core.dto.request;

import java.time.LocalDateTime;

public record UpdateConsultationDTO(LocalDateTime dateAndHour) { }
