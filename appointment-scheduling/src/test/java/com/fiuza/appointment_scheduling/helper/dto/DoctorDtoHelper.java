package com.fiuza.appointment_scheduling.helper.dto;

import com.fiuza.appointment_scheduling.core.dto.request.DoctorDTO;
import com.fiuza.appointment_scheduling.core.enums.Specialty;

public class DoctorDtoHelper {

    public static DoctorDTO defaultDTO() {
        return new DoctorDTO(
                "Doutor(a) teste",
                "CRM/SP 123456",
                Specialty.CLINICO_GERAL
        );
    }

    public static DoctorDTO withNull() {
        return new DoctorDTO(
                "Doutor(a) teste",
                null,
                Specialty.CLINICO_GERAL
        );
    }
}
