package com.fiuza.appointment_scheduling.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiuza.appointment_scheduling.application.controllers.PatientController;
import com.fiuza.appointment_scheduling.application.handlers.ControllerExceptionHandler;
import com.fiuza.appointment_scheduling.core.dto.request.PatientDTO;
import com.fiuza.appointment_scheduling.core.entities.patient.Patient;
import com.fiuza.appointment_scheduling.core.gateway.PatientGateway;
import com.fiuza.appointment_scheduling.core.usecases.patient.CreatePatientUseCase;
import com.fiuza.appointment_scheduling.helper.dto.PatientDtoHelper;
import com.fiuza.appointment_scheduling.helper.entities.patient.PatientHelper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    CreatePatientUseCase createPatientUseCase;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    PatientController patientController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        mock = MockitoAnnotations.openMocks(this);
        patientController = new PatientController(createPatientUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController)
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
    public void create_patient_with_success() throws Exception {
        //arrange
        PatientDTO patientDTO = PatientDtoHelper.defaultDto();
        Patient patient = PatientHelper.createPatientWithId();

        when(createPatientUseCase.execute(any(PatientDTO.class))).thenReturn(patient);

        //act & assert
        mockMvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().isCreated());

        verify(createPatientUseCase, times(1)).execute(any(PatientDTO.class));

    }
}
