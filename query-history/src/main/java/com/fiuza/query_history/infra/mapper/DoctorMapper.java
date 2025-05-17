package com.fiuza.query_history.infra.mapper;

import com.example.shared.entities.DoctorModel;
import com.fiuza.query_history.core.entities.Doctor;
import com.fiuza.query_history.core.enums.Specialty;

import java.util.Collections;

public class DoctorMapper {
    public static Doctor doctorModelToDoctor(DoctorModel doctorModel) {
        var specialty = Specialty.valueOf(doctorModel.getSpecialtyModel().name());
        //mesma coisa sobre a list
        return new Doctor(
                doctorModel.getId(),
                doctorModel.getName(),
                doctorModel.getCrm(),
                specialty,
                Collections.emptyList()
        );
    }
}
