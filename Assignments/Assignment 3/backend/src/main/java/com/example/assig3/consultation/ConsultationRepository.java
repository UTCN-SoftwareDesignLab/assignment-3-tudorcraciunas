package com.example.assig3.consultation;

import com.example.assig3.consultation.model.Consultation;
import com.example.assig3.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long>, JpaSpecificationExecutor<Consultation> {
    Consultation findConsultationByDoctorAndStartDate(User doctor, Date startDate);

    List<Consultation> findConsultationsByPatientId(Long patientId);
}
