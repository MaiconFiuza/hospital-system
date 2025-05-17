package com.fiuza.order_notification.application.listener;

import com.example.shared.entities.dto.AppointmentEventDTO;
import com.fiuza.order_notification.helper.dto.AppointmentEventDTOHelper;
import com.fiuza.order_notification.infra.SendAppointmentReminderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AppointmentEventListenerTest {

    private SendAppointmentReminderUseCase reminderUseCase;
    private AppointmentEventListener listener;

    @BeforeEach
    void setup() {
        reminderUseCase = Mockito.mock(SendAppointmentReminderUseCase.class);
        listener = new AppointmentEventListener(reminderUseCase);
    }

    @Test
    void shouldHandleAppointmentEvent() {
        AppointmentEventDTO dto = AppointmentEventDTOHelper.create();
        listener.onAppointmentEvent(dto);
        Mockito.verify(reminderUseCase).execute(dto);
    }

    @Test
    void shouldHandleCanceledAppointmentEvent() {
        AppointmentEventDTO dto = AppointmentEventDTOHelper.canceled();
        listener.canceledAppointmentEvent(dto);
        Mockito.verify(reminderUseCase).execute(dto);
    }

    @Test
    void shouldHandleUpdatedAppointmentEvent() {
        AppointmentEventDTO dto = AppointmentEventDTOHelper.update();
        listener.updatedAppointmentEvent(dto);
        Mockito.verify(reminderUseCase).execute(dto);
    }
}
