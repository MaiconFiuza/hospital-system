package com.fiuza.appointment_scheduling.core.usecases.consultation;

import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.gateway.AppointmentEventGateway;
import com.fiuza.appointment_scheduling.core.gateway.ConsultationGateway;

public class DeleteConsultationUseCase {
    private ConsultationGateway consultationGateway;
    private AppointmentEventGateway appointmentEventGateway;

    public DeleteConsultationUseCase(
            ConsultationGateway consultationGateway,
            AppointmentEventGateway appointmentEventGateway
    ) {
        this.appointmentEventGateway = appointmentEventGateway;
        this.consultationGateway = consultationGateway;
    }

    public void execute(Long id) {
        try {
            var consultation = consultationGateway.findConsultationById(id)
                    .orElseThrow(() -> new NotFoundException("Consulta não foi encontrada"));

            consultationGateway.delete(id);
            var appointment = new Appointment(
                    consultation.getPatient().getName(),
                    consultation.getDoctor().getName(),
                    consultation.getDateAndHour(),
                    "Aviso de consulta cancelada, caso haja dúvida por favor entrar em contato"
            );
            appointmentEventGateway.publishAppointmentCanceled(appointment);

        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new InternalServerError("Por favor tente novamente");
        }
    }
}
