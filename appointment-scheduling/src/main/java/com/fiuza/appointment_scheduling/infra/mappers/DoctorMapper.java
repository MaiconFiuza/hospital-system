package com.fiuza.appointment_scheduling.infra.mappers;

import com.example.shared.entities.DoctorModel;
import com.example.shared.entities.enums.SpecialtyModel;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.enums.Specialty;

import java.util.Collections;

public class DoctorMapper {

    public static DoctorModel doctorToDoctorModel(Doctor doctor) {
        var specialty = SpecialtyModel.valueOf(doctor.getSpecialty().name());
        return new DoctorModel(
                doctor.getId(),
                doctor.getName(),
                doctor.getCrm(),
                specialty,
                Collections.emptyList()
        );
    }

    public static Doctor doctorModelToDoctor(DoctorModel doctorModel) {
        var specialty = Specialty.valueOf(doctorModel.getSpecialtyModel().name());
        return new Doctor(
                doctorModel.getId(),
                doctorModel.getName(),
                doctorModel.getCrm(),
                specialty,
                Collections.emptyList()
        );
    }
}
