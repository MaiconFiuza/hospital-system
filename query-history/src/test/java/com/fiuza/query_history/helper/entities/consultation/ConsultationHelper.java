package com.fiuza.query_history.helper.entities.consultation;

import com.fiuza.query_history.core.entities.Consultation;
import com.fiuza.query_history.core.enums.Specialty;
import com.fiuza.query_history.helper.entities.doctor.DoctorHelper;
import com.fiuza.query_history.helper.entities.nurse.NurseHelper;
import com.fiuza.query_history.helper.entities.patient.PatientHelper;

import java.time.LocalDateTime;

public class ConsultationHelper {
    public static Consultation createConsultationDefault() {
        return new Consultation(
                LocalDateTime.of(2025,07,03,10, 15),
                PatientHelper.createPatientWithId(),
                NurseHelper.createNurseWithId(),
                DoctorHelper.createDoctorWithId(),
                Specialty.CLINICO_GERAL
        );
    }

    public static Consultation createConsultationWithId() {
        return new Consultation(
                1L,
                LocalDateTime.of(2025,07,03,10, 15),
                PatientHelper.createPatientWithId(),
                NurseHelper.createNurseWithId(),
                DoctorHelper.createDoctorWithId(),
                Specialty.CLINICO_GERAL
        );
    }
}
