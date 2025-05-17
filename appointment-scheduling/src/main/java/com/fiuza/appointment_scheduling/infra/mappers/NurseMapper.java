package com.fiuza.appointment_scheduling.infra.mappers;

import com.example.shared.entities.NurseModel;
import com.fiuza.appointment_scheduling.core.entities.nurse.Nurse;

import java.util.Collections;

public class NurseMapper {

    public static NurseModel nurseToNurseModel(Nurse nurse) {
        return new NurseModel(
                nurse.getId(),
                nurse.getName(),
                nurse.getCip(),
                Collections.emptyList()
        );
    }

    public static Nurse nurseModelToNurse(NurseModel nurseModel) {
        return new Nurse(
                nurseModel.getId(),
                nurseModel.getName(),
                nurseModel.getCip(),
                Collections.emptyList()
        );
    }
}
