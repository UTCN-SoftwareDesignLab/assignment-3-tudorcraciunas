package com.example.assig3.patient;


import com.example.assig3.patient.dto.PatientDto;
import com.example.assig3.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto toDto(Patient patient);

    Patient fromDto(PatientDto patientDto);
}
