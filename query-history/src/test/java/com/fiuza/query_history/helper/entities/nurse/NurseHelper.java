package com.fiuza.query_history.helper.entities.nurse;

import com.fiuza.query_history.core.entities.Nurse;

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
