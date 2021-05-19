package com.example.assig3.consultation;

import com.example.assig3.consultation.dto.ConsultationDto;
import com.example.assig3.consultation.mapper.ConsultationMapper;
import com.example.assig3.consultation.model.Consultation;
import com.example.assig3.patient.PatientRepository;
import com.example.assig3.patient.model.Patient;
import com.example.assig3.user.UserRepository;
import com.example.assig3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public List<Consultation> findAll(){
        return consultationRepository.findAll();
    }

    public List<ConsultationDto> findAllDto(){
        List<Consultation> consultations = consultationRepository.findAll();

        return consultations.stream()
                .map(consultationMapper::toDto).collect(Collectors.toList());
    }

    public ConsultationDto findById(Long id){
        return consultationRepository.findById(id)
                .map(consultationMapper::toDto).orElseThrow( () -> new EntityNotFoundException("Consultation id not found: " + id));

    }

    public void deleteById(Long id){
        consultationRepository.deleteById(id);
    }

    public ConsultationDto save(ConsultationDto consultationDto){
        User doctor = userRepository.findById(consultationDto.getDoctorId()).orElseThrow( () -> new EntityNotFoundException("Doctor not found with id: " + consultationDto.getDoctorId()));

        Patient patient = patientRepository.findById(consultationDto.getPatientId()).orElseThrow( () -> new EntityNotFoundException("Patient not found with id: " + consultationDto.getPatientId()));

        Consultation alreadyBooked = consultationRepository.findConsultationByDoctorAndStartDate(doctor, consultationDto.getStartDate());

        if(alreadyBooked != null){
            throw new RuntimeException("Doctor booked for the date.");
        }
        else{
            Consultation consultation = Consultation.builder()
                    .doctor(doctor)
                    .patient(patient)
                    .startDate(consultationDto.getStartDate())
                    .endDate(consultationDto.getEndDate())
                    .description(consultationDto.getDescription())
                    .build();
            return consultationMapper.toDto(consultationRepository.save(consultation));
        }
    }

    public List<ConsultationDto> findByPatientId(Long patientId){
        List<Consultation> patientConsultations = consultationRepository.findConsultationsByPatientId(patientId);

        return patientConsultations.stream()
                .map(consultationMapper::toDto).collect(Collectors.toList());
    }

    public ConsultationDto update(Long id, ConsultationDto consultationDto){
        Consultation consultation = consultationMapper.fromDto(consultationDto);
        consultation.setId(id);

        return consultationMapper.toDto(consultationRepository.save(consultation));
    }

}
