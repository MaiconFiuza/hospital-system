package com.example.shared.entities.helper;

import com.example.shared.entities.DoctorModel;
import com.example.shared.entities.enums.SpecialtyModel;

import java.util.Collections;

public class DoctorModelHelper {

    public static DoctorModel createDoctorDefault() {
        return new DoctorModel(
                1L,
                "Doutor(a) teste",
                "CRM/SP 123456",
                SpecialtyModel.CLINICO_GERAL,
                Collections.emptyList()
        );
    }
}
