package com.example.assig3.consultation.model;

import com.example.assig3.patient.model.Patient;
import com.example.assig3.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "doctor_id")
    @NotNull
    private User doctor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endDate;

    @Column
    private String description;

}
