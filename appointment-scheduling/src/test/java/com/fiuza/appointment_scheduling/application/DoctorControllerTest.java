package com.fiuza.appointment_scheduling.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.appointment_scheduling.application.controllers.DoctorController;
import com.fiuza.appointment_scheduling.application.handlers.ControllerExceptionHandler;
import com.fiuza.appointment_scheduling.core.dto.request.DoctorDTO;
import com.fiuza.appointment_scheduling.core.entities.doctor.Doctor;
import com.fiuza.appointment_scheduling.core.usecases.doctor.CreateDoctorUseCase;
import com.fiuza.appointment_scheduling.helper.dto.DoctorDtoHelper;
import com.fiuza.appointment_scheduling.helper.entities.doctor.DoctorHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    CreateDoctorUseCase createDoctorUseCase;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    DoctorController doctorController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        doctorController = new DoctorController(createDoctorUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    public void create_doctor_with_success() throws Exception {
        //arrange
        DoctorDTO doctorDTO = DoctorDtoHelper.defaultDTO();
        Doctor doctor = DoctorHelper.createDoctorWithId();

        when(createDoctorUseCase.execute(any(DoctorDTO.class))).thenReturn(doctor);

        //act & assert
        mockMvc.perform(post("/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(status().isCreated());

        verify(createDoctorUseCase, times(1)).execute(any(DoctorDTO.class));

    }

}
