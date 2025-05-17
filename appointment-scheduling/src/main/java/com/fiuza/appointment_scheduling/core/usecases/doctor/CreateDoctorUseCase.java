package com.fiuza.appointment_scheduling.core.usecases.doctor;

import com.fiuza.appointment_scheduling.core.dto.request.DoctorDTO;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.exceptions.AlreadyExistException;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.gateway.DoctorGateway;

public class CreateDoctorUseCase {
    private final DoctorGateway doctorGateway;

    public CreateDoctorUseCase(DoctorGateway doctorGateway) {
        this.doctorGateway = doctorGateway;
    }

    public Doctor execute(DoctorDTO doctorDTO) {
        try {
            var alreadyExistDoctor = doctorGateway.findDoctorByCrmValidation(doctorDTO.crm());

            if(alreadyExistDoctor) {
                throw new AlreadyExistException("Médico já possuí cadastro na nossa rede");
            }

            Doctor doctor = new Doctor(doctorDTO.name(), doctorDTO.crm(), doctorDTO.specialty());

            return doctorGateway.save(doctor);
        } catch (AlreadyExistException e) {
            throw new AlreadyExistException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
