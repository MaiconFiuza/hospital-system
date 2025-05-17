package com.fiuza.appointment_scheduling.core.usecases.consultation;

import com.fiuza.appointment_scheduling.core.dto.request.UpdateConsultationDTO;
import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.gateway.AppointmentEventGateway;
import com.fiuza.appointment_scheduling.core.gateway.ConsultationGateway;

public class UpdateConsultationUseCase {
    private final ConsultationGateway consultationGateway;
    private final AppointmentEventGateway appointmentEventGateway;


    public UpdateConsultationUseCase(
            ConsultationGateway consultationGateway,
            AppointmentEventGateway appointmentEventGateway
    ) {
        this.consultationGateway = consultationGateway;
        this.appointmentEventGateway = appointmentEventGateway;
    }

    public Consultation execute(UpdateConsultationDTO updateConsultationDTO, Long id) {
        try {
            var consultation = consultationGateway.findConsultationById(id)
                    .orElseThrow(() -> new NotFoundException("Consulta não foi encontrada"));

            var updatedConsultation = new Consultation(
                    consultation.getId(),
                    updateConsultationDTO.dateAndHour(),
                    consultation.getPatient(),
                    consultation.getNurse(),
                    consultation.getDoctor(),
                    consultation.getSpecialty()
            );

            var appointment = new Appointment(
                    consultation.getPatient().getName(),
                    consultation.getDoctor().getName(),
                    consultation.getDateAndHour(),
                    "Aviso de remarcação de consulta"
            );

            appointmentEventGateway.publishAppointmentUpdated(appointment);


            return consultationGateway.save(updatedConsultation);

        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError("Por favor tente novamente");
        }
    }
}
