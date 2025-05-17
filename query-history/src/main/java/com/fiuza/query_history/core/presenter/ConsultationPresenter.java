package com.fiuza.query_history.core.presenter;

import com.fiuza.query_history.core.dto.response.ConsultationDoctorResponseDTO;
import com.fiuza.query_history.core.dto.response.ConsultationNurseResponseDTO;
import com.fiuza.query_history.core.dto.response.ConsultationPatientResponseDTO;
import com.fiuza.query_history.core.dto.response.ConsultationResponseDTO;
import com.fiuza.query_history.core.entities.Consultation;

import java.util.List;

public class ConsultationPresenter {

    public static List<ConsultationResponseDTO> toConsultationResponseDtoList(List<Consultation> consultations) {
        return consultations.stream()
                .map(consultation -> {
                    ConsultationDoctorResponseDTO doctorDTO = new ConsultationDoctorResponseDTO(
                            consultation.getDoctor().getCrm(),
                            consultation.getDoctor().getName()
                    );

                    ConsultationNurseResponseDTO nurseDTO = new ConsultationNurseResponseDTO(
                            consultation.getNurse().getCip(),
                            consultation.getNurse().getName()
                    );

                    ConsultationPatientResponseDTO patientDTO = new ConsultationPatientResponseDTO(
                            consultation.getPatient().getCpf(),
                            consultation.getPatient().getName()
                    );

                    return new ConsultationResponseDTO(
                            consultation.getId(),
                            doctorDTO,
                            nurseDTO,
                            patientDTO,
                            consultation.getDateAndHour(),
                            consultation.getSpecialty()
                    );
                })
                .toList();
    }
}
