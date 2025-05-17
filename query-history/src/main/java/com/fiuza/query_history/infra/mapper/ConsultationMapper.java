package com.fiuza.query_history.infra.mapper;

import com.example.shared.entities.ConsultationModel;
import com.fiuza.query_history.core.entities.Consultation;
import com.fiuza.query_history.core.enums.Specialty;

import java.util.List;

public class ConsultationMapper {
    public static List<Consultation> consultationModelListToConsultationList(List<ConsultationModel> consultationModels) {
        return consultationModels.stream()
                .map(ConsultationMapper::consultationModelToConsultation)
                .toList();
    }

    private static Consultation consultationModelToConsultation(ConsultationModel consultationModel) {
        var specialty = Specialty.valueOf(consultationModel.getSpecialty().name());
        return new Consultation(
                consultationModel.getId(),
                consultationModel.getDateAndHour(),
                PatientMapper.patientModelToPatient(consultationModel.getPatient()),
                NurseMapper.nurseModelToNurse(consultationModel.getNurse()),
                DoctorMapper.doctorModelToDoctor(consultationModel.getDoctor()),
                specialty
        );
    }

}
