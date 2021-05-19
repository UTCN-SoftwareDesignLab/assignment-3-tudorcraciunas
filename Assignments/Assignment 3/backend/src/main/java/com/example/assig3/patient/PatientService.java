package com.example.assig3.patient;

import com.example.assig3.patient.dto.PatientDto;
import com.example.assig3.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public List<PatientDto> findAllDto(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(patientMapper::toDto).collect(Collectors.toList());
    }

    private Patient findById(Long id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient id not found: " + id));
    }

    public PatientDto findByIdDto(Long id){
        return patientMapper.toDto(findById(id));
    }


    public PatientDto create(PatientDto patientDto){
        return patientMapper.toDto(patientRepository.save(
                patientMapper.fromDto(patientDto)
        ));
    }

    public PatientDto edit(Long id, PatientDto patientDto){
        Patient patient = findById(id);

        patient.setName(patientDto.getName());
        patient.setAddress(patientDto.getAddress());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setIdCardNumber(patientDto.getIdCardNumber());
        patient.setPersonalNumericalCode(patientDto.getPersonalNumericalCode());

        return patientMapper.toDto(patientRepository.save(patient));
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }


    public void addDummyData() {
        Random random = new Random();

        patientRepository.save(
                Patient.builder()
                        .name(String.valueOf(random.nextInt()))
                        .address(String.valueOf(random.nextInt()))
                        .birthDate(LocalDate.now())
                        .idCardNumber(String.valueOf(random.nextDouble()))
                        .personalNumericalCode(String.valueOf(random.nextDouble()))
                    .build());
    }
}
