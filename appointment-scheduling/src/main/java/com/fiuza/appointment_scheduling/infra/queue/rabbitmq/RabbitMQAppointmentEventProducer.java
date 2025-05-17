package com.fiuza.appointment_scheduling.infra.queue.rabbitmq;


import com.example.shared.entities.dto.AppointmentEventDTO;
import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.gateway.AppointmentEventGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQAppointmentEventProducer implements AppointmentEventGateway {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQAppointmentEventProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishAppointmentCreated(Appointment appointment) {
                AppointmentEventDTO dto = new AppointmentEventDTO(
                        appointment.patientName(),
                        appointment.doctorName(),
                        appointment.dateAndHour(),
                        appointment.reasonForContact()
                );
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_NEW, dto);
    }

    @Override
    public void publishAppointmentUpdated(Appointment appointment) {
        AppointmentEventDTO dto = new AppointmentEventDTO(
                appointment.patientName(),
                appointment.doctorName(),
                appointment.dateAndHour(),
                appointment.reasonForContact()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_UPDATED, dto);

    }

    @Override
    public void publishAppointmentCanceled(Appointment appointment) {
        AppointmentEventDTO dto = new AppointmentEventDTO(
                appointment.patientName(),
                appointment.doctorName(),
                appointment.dateAndHour(),
                appointment.reasonForContact()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_CANCELED, dto);
    }
}
