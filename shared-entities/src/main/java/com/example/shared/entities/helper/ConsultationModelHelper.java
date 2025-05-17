package com.example.shared.entities.helper;

import com.example.shared.entities.ConsultationModel;
import com.example.shared.entities.enums.SpecialtyModel;

import java.time.LocalDateTime;

public class ConsultationModelHelper {

    public static ConsultationModel createConsultationDefault() {
        return new ConsultationModel(
                1L,
                LocalDateTime.of(2025,07,03,10, 15),
                SpecialtyModel.CLINICO_GERAL,
                PatientModelHelper.createPatientDefault(),
                NurseModelHelper.createNurseDefault(),
                DoctorModelHelper.createDoctorDefault()
        );
    }
}
