package com.fiuza.appointment_scheduling.core.usecases.patient;

import com.fiuza.appointment_scheduling.core.dto.request.PatientDTO;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.exceptions.AlreadyExistException;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.gateway.PatientGateway;

public class CreatePatientUseCase {
    private final PatientGateway patientGateway;

    public CreatePatientUseCase(PatientGateway patientGateway) {
        this.patientGateway = patientGateway;
    }

    public Patient execute(PatientDTO patientDTO) {
        try {
            var hasUser = patientGateway.findPatientByCpfValidation(patientDTO.cpf());

            if(hasUser) {
                throw new AlreadyExistException("Número de cpf já está em uso");
            }

            Patient patient = new Patient(patientDTO.name(), patientDTO.cpf(), patientDTO.birthdate());

            return patientGateway.save(patient);

        } catch (AlreadyExistException e) {
            throw new AlreadyExistException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

}
