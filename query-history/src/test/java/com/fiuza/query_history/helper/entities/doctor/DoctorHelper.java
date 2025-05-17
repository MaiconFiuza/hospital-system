package com.fiuza.query_history.helper.entities.doctor;

import com.fiuza.query_history.core.entities.Doctor;
import com.fiuza.query_history.core.enums.Specialty;

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
