package com.fiuza.appointment_scheduling.helper.entities.consultation;

import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.enums.Specialty;
import com.fiuza.appointment_scheduling.helper.entities.doctor.DoctorHelper;
import com.fiuza.appointment_scheduling.helper.entities.nurse.NurseHelper;
import com.fiuza.appointment_scheduling.helper.entities.patient.PatientHelper;

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
