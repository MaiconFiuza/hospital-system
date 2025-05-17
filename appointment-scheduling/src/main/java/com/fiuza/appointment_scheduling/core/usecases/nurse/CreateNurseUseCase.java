package com.fiuza.appointment_scheduling.core.usecases.nurse;

import com.fiuza.appointment_scheduling.core.dto.request.NurseDTO;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;
import com.fiuza.appointment_scheduling.core.exceptions.AlreadyExistException;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.gateway.NurseGateway;

public class CreateNurseUseCase {
    private final NurseGateway nurseGateway;

    public CreateNurseUseCase(NurseGateway nurseGateway) {
        this.nurseGateway = nurseGateway;
    }

    public Nurse execute(NurseDTO nurseDTO) {
        try {
            var alreadyExistsNurse = nurseGateway.findNurseByCipValidation(nurseDTO.cip());

            if(alreadyExistsNurse) {
                throw new AlreadyExistException("Enfermeiro(a) já se encontra cadastrado(a) no nosso sistema");
            }

            Nurse nurse = new Nurse(nurseDTO.name(), nurseDTO.cip());

            return nurseGateway.save(nurse);
        } catch (AlreadyExistException e) {
            throw new AlreadyExistException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
