package com.fiuza.appointment_scheduling.helper.entities.nurse;

import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;

import java.util.Collections;

public class NurseHelper {

    public static Nurse createNurseDefault() {
        return new Nurse(
                "Enfermeiro(a) teste",
                "COREN-SP-123456"
        );
    }

    public static Nurse createNurseWithId() {
        return new Nurse(
                1L,
                "Enfermeiro(a) teste",
                "COREN-SP-123456",
                Collections.emptyList()
        );
    }
}
