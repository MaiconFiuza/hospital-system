package com.fiuza.appointment_scheduling.infra.mappers;

import com.example.shared.entities.ConsultationModel;
import com.example.shared.entities.enums.SpecialtyModel;
import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.enums.Specialty;

public class ConsultationMapper {

    public static ConsultationModel consultationToConsultationModel(Consultation consultation) {
        var specialty = SpecialtyModel.valueOf(consultation.getSpecialty().name());
        return new ConsultationModel(
                consultation.getId(),
                consultation.getDateAndHour(),
                specialty,
                PatientMapper.patientToPatientModel(consultation.getPatient()),
                NurseMapper.nurseToNurseModel(consultation.getNurse()),
                DoctorMapper.doctorToDoctorModel(consultation.getDoctor())
        );
    }

    public static Consultation consultationModelToConsultation(ConsultationModel consultationModel) {
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
