package com.example.assig3.patient;

import com.example.assig3.BaseControllerTest;
import com.example.assig3.TestCreationFactory;
import com.example.assig3.patient.dto.PatientDto;
import com.example.assig3.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.assig3.UrlMapping.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;


class PatientControllerTest extends BaseControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        patientController = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }


    @Test
    void findAll() throws Exception {

        List<PatientDto> patientDTOS = TestCreationFactory.listOf(PatientDto.class);
        when(patientService.findAllDto()).thenReturn(patientDTOS);

        ResultActions response = mockMvc.perform(get(PATIENT));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTOS));

    }


    @Test
    void create() throws Exception {
        PatientDto patientDto = TestCreationFactory.object(PatientDto.class);

        when(patientService.create(patientDto)).thenReturn(patientDto);

        ResultActions result = performPostWithRequestBody(PATIENT, patientDto);
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDto));
    }
}