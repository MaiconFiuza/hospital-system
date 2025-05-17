package com.fiuza.order_notification.infra.config;

import com.fiuza.order_notification.infra.SendAppointmentReminderUseCase;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentReminderCaseConfig {
    public SendAppointmentReminderUseCase reminderUseCase() {
        return new SendAppointmentReminderUseCase();
    }
}
