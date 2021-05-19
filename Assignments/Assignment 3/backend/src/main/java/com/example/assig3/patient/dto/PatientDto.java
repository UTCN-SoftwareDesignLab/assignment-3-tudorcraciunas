package com.example.assig3.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    @Builder.Default
    private Long id = -1L;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String idCardNumber = "";

    @Builder.Default
    private String personalNumericalCode = "";

    @Builder.Default
    private LocalDate birthDate = LocalDate.now();

    @Builder.Default
    private String address = "";
}
