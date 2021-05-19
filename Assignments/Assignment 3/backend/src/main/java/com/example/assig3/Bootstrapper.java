package com.example.assig3;


import com.example.assig3.consultation.ConsultationRepository;
import com.example.assig3.consultation.ConsultationService;
import com.example.assig3.consultation.dto.ConsultationDto;
import com.example.assig3.patient.PatientRepository;
import com.example.assig3.patient.PatientService;
import com.example.assig3.patient.dto.PatientDto;
import com.example.assig3.security.AuthService;
import com.example.assig3.security.dto.SignupRequest;
import com.example.assig3.user.RoleRepository;
import com.example.assig3.user.UserRepository;
import com.example.assig3.user.model.ERole;
import com.example.assig3.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientService patientService;

    private final ConsultationService consultationService;

    private final ConsultationRepository consultationRepository;

    private final PatientRepository patientRepository;


    @Value("${app.bootstrap}")
    private Boolean bootstrap;



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {


            consultationRepository.deleteAll();
            patientRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("tudor@email.com")
                    .username("admin")
                    .password("admin")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("user2@email.com")
                    .username("doctor")
                    .password("doctor")
                    .roles(Set.of("DOCTOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("user1@email.com")
                    .username("secretary")
                    .password("secretary")
                    .roles(Set.of("SECRETARY"))
                    .build());
            patientService.create(PatientDto.builder()
                    .name("Pacientul Test")
                    .address("Strada Reusitei, nr. 42")
                    .birthDate(LocalDate.now())
                    .idCardNumber("1234 4321 2341")
                    .personalNumericalCode("199 09 24 21 51")
                    .build());
            consultationService.save(ConsultationDto.builder()
                    .description("Prima consultatie oferita la clinica.")
                    .doctorId(userRepository.findByUsername("doctor").get().getId())
                    .patientId(patientService.findAll().get(0).getId())
                    .startDate(Date.valueOf(LocalDate.now()))
                    .endDate(Date.valueOf(LocalDate.now()))
                    .build());

        }
    }
}
