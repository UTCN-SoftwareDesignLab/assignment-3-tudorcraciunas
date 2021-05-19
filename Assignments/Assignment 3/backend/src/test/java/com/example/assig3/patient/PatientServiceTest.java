package com.example.assig3.patient;

import com.example.assig3.TestCreationFactory;
import com.example.assig3.patient.model.Patient;
import com.example.assig3.patient.dto.PatientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void findAll() {
        List<Patient> patients = TestCreationFactory.listOf(Patient.class);
        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDto> all = patientService.findAllDto();

        Assertions.assertEquals(patients.size(), all.size());


    }

    @Test
    void create() {
        PatientDto patientDTO = TestCreationFactory.object(PatientDto.class);
        Patient patient = patientMapper.fromDto(patientDTO);

        when(patientMapper.fromDto(patientDTO)).thenReturn(patient);
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);
        when(patientRepository.save(patient)).thenReturn(patient);

        Assertions.assertEquals(patientDTO.getId(), patientService.create(patientDTO).getId());
    }


}