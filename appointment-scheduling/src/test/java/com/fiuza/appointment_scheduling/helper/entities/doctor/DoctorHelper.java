package com.fiuza.appointment_scheduling.helper.entities.doctor;

import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.enums.Specialty;

import java.util.Collections;

public class DoctorHelper {

    public static Doctor createDoctorDefault() {
        return new Doctor(
                "Doutor(a) teste",
                "CRM/SP 123456",
                Specialty.CLINICO_GERAL
        );
    }

    public static Doctor createDoctorWithId() {
        return new Doctor(
                1L,
                "Doutor(a) teste",
                "CRM/SP 123456",
                Specialty.CLINICO_GERAL,
                Collections.emptyList()
        );
    }
}
