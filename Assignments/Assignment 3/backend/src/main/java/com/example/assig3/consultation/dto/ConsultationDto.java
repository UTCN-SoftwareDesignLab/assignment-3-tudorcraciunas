package com.example.assig3.consultation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDto {
    private Date startDate;
    private Date endDate;
    private String description;
    private Long id;
    private Long doctorId;
    private Long patientId;
}
