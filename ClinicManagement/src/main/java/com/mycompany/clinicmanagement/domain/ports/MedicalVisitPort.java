package com.mycompany.clinicmanagement.domain.ports;


import com.mycompany.clinicmanagement.domain.models.MedicalVisit;
import java.util.List;

public interface MedicalVisitPort {
    MedicalVisit save(MedicalVisit visit);
    MedicalVisit findById(String visitId);
    List<MedicalVisit> findByPatient(String patientId);
    List<MedicalVisit> findAll();
    void delete(String visitId);
}

