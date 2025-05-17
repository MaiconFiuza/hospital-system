package com.fiuza.query_history.infra.mapper;

import com.example.shared.entities.NurseModel;
import com.fiuza.query_history.core.entities.Nurse;

import java.util.Collections;

public class NurseMapper {
    public static Nurse nurseModelToNurse(NurseModel nurseModel) {
        //depois tem que lidar com a list
        return new Nurse(
                nurseModel.getId(),
                nurseModel.getName(),
                nurseModel.getCip(),
                Collections.emptyList()
        );
    }
}
