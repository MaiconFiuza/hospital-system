package com.fiuza.appointment_scheduling.core.gateway;

import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;

import java.util.Optional;

public interface NurseGateway {
    Nurse save(Nurse nurse);

    Optional<Nurse> findNurseById(Long id);

    Optional<Nurse> findNurseByCip(String cip);

    boolean findNurseByCipValidation(String cpf);

}
