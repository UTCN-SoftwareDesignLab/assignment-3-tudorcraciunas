package com.example.assig3.patient.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String idCardNumber;

    @Column(length = 15, nullable = false)
    private String personalNumericalCode;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(length = 64)
    private String address;


}
