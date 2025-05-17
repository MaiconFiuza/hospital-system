package com.fiuza.order_notification.infra;

import com.example.shared.entities.dto.AppointmentEventDTO;
import com.fiuza.order_notification.core.gateway.SendAppointmentReminderGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SendAppointmentReminderUseCase implements SendAppointmentReminderGateway {
    private static final Logger log = LoggerFactory.getLogger(SendAppointmentReminderUseCase.class);

    @Override
    public void execute(AppointmentEventDTO appointmentEventDTO) {
        log.info("Notificação : {}", appointmentEventDTO);
    }
}
