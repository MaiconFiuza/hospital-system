package com.fiuza.order_notification.core.gateway;

import com.example.shared.entities.dto.AppointmentEventDTO;

public interface SendAppointmentReminderGateway {
    void execute(AppointmentEventDTO appointmentEventDTO);
}
