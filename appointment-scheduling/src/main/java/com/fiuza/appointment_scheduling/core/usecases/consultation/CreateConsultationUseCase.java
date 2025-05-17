package com.fiuza.appointment_scheduling.core.usecases.consultation;

import com.fiuza.appointment_scheduling.core.dto.request.ConsultationDTO;
import com.fiuza.appointment_scheduling.core.dto.response.Appointment;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.exceptions.NullDataNotNullException;
import com.fiuza.appointment_scheduling.core.gateway.ConsultationGateway;
import com.fiuza.appointment_scheduling.core.gateway.DoctorGateway;
import com.fiuza.appointment_scheduling.core.gateway.NurseGateway;
import com.fiuza.appointment_scheduling.core.gateway.PatientGateway;
import com.fiuza.appointment_scheduling.core.gateway.AppointmentEventGateway;

public class CreateConsultationUseCase {
    private final ConsultationGateway consultationGateway;
    private final PatientGateway patientGateway;
    private final NurseGateway nurseGateway;
    private final DoctorGateway doctorGateway;
    private final AppointmentEventGateway appointmentEventGateway;

    public CreateConsultationUseCase(
       ConsultationGateway consultationGateway,
       PatientGateway patientGateway,
       NurseGateway nurseGateway,
       DoctorGateway doctorGateway,
       AppointmentEventGateway appointmentEventGateway
    ) {
        this.consultationGateway = consultationGateway;
        this.patientGateway = patientGateway;
        this.nurseGateway = nurseGateway;
        this.doctorGateway = doctorGateway;
        this.appointmentEventGateway = appointmentEventGateway;
    }

    public Consultation execute(ConsultationDTO consultationDTO) {
        try {
            var patient = patientGateway.findPatientByCpf(consultationDTO.cpf());
            if (patient.isEmpty()) {
                throw new NotFoundException("Paciente não econtrado por favor verifique o CPF digitado");
            }

            var nurse = nurseGateway.findNurseByCip(consultationDTO.cip());
            if(nurse.isEmpty()) {
                throw new NotFoundException("Enfermeiro(a) não econtrado(a) por favor verifique o CIP digitado");
            }

            var doctor = doctorGateway.findDoctorByCrm(consultationDTO.crm());
            if(doctor.isEmpty()) {
                throw new NotFoundException("Doutor(a) não econtrado(a) por favor verifique o CRM digitado");
            }

            Consultation consultation = new Consultation(
                    consultationDTO.dateAndHour(),
                    patient.get(),
                    nurse.get(),
                    doctor.get(),
                    consultationDTO.specialty()
            );

            var appointment = new Appointment(
                    consultation.getPatient().getName(),
                    consultation.getDoctor().getName(),
                    consultation.getDateAndHour(),
                    "Aviso de marcação de consulta"
            );
            appointmentEventGateway.publishAppointmentCreated(appointment);

            return consultationGateway.save(consultation);

        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (NullDataNotNullException e) {
            throw new NullDataNotNullException("Todos os campos devem ser enviados");
        } catch (Exception e) {
            throw new InternalServerError("Erro interno, por favor tentar novamente");
        }

    }
}
