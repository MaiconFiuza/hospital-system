package com.fiuza.appointment_scheduling.core.dto.request;

import com.fiuza.appointment_scheduling.core.enums.Specialty;

public record DoctorDTO( String name, String crm, Specialty specialty) { }
