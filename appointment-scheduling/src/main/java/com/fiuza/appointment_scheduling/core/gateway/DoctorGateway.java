package com.fiuza.appointment_scheduling.core.gateway;

import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;

import java.util.Optional;

public interface DoctorGateway {
    Doctor save(Doctor doctor);

    Optional<Doctor> findDoctorById(Long id);

    Optional<Doctor> findDoctorByCrm(String crm);

    boolean findDoctorByCrmValidation(String crm);

}
