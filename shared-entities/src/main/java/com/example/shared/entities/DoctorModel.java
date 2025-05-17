package com.example.shared.entities;

import com.example.shared.entities.enums.SpecialtyModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String crm;
    private SpecialtyModel specialtyModel;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<ConsultationModel> consultationModels = new ArrayList<>();
}
