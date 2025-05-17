package com.fiuza.appointment_scheduling.helper.dto;

import com.fiuza.appointment_scheduling.core.dto.request.NurseDTO;

public class NurseDtoHelper {

    public static NurseDTO defaultDto() {
        return new NurseDTO(
                "Enfermeiro(a) teste",
                "COREN-SP-123456"
        );
    }

    public static NurseDTO withNull() {
        return new NurseDTO(
                "Enfermeiro(a) teste",
                null
        );
    }
}
