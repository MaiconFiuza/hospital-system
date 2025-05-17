package com.example.shared.entities.helper;

import com.example.shared.entities.NurseModel;

import java.util.Collections;

public class NurseModelHelper {

    public static NurseModel createNurseDefault() {
        return new NurseModel(
                1L,
                "Enfermeiro(a) teste",
                "COREN-SP-123456",
                Collections.emptyList()
        );
    }
}
