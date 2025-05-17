package com.fiuza.appointment_scheduling.core.gateway;

import com.fiuza.appointment_scheduling.core.dto.response.Appointment;

public interface AppointmentEventGateway {
    void publishAppointmentCreated(Appointment appointment);
    void publishAppointmentUpdated(Appointment appointment);
    void publishAppointmentCanceled(Appointment appointment);
}
