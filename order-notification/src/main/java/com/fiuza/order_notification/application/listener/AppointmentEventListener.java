package com.fiuza.order_notification.application.listener;

import com.example.shared.entities.dto.AppointmentEventDTO;
import com.fiuza.order_notification.infra.SendAppointmentReminderUseCase;
import com.fiuza.order_notification.infra.queue.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentEventListener {

    private final SendAppointmentReminderUseCase reminderUseCase;

    public AppointmentEventListener(SendAppointmentReminderUseCase reminderUseCase) {
        this.reminderUseCase = reminderUseCase;
    }

    @RabbitListener(queues = RabbitMQConfig.APPOINTMENT_EVENT_QUEUE)
    public void onAppointmentEvent(AppointmentEventDTO dto) {
        reminderUseCase.execute(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.APPOINTMENT_CANCELED_QUEUE)
    public void canceledAppointmentEvent(AppointmentEventDTO dto) {
        reminderUseCase.execute(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.APPOINTMENT_UPDATED_QUEUE)
    public void updatedAppointmentEvent(AppointmentEventDTO dto) {
        reminderUseCase.execute(dto);
    }

}
