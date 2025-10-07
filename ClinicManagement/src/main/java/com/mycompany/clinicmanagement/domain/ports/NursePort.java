package com.mycompany.clinicmanagement.domain.ports;

import com.mycompany.clinicmanagement.domain.models.Nurse;
import java.util.List;

public interface NursePort {
    Nurse save(Nurse nurse);
    Nurse findById(String nurseId);
    List<Nurse> findAll();
    List<Nurse> findBySpecialization(String specialization);
    void delete(String nurseId);
}

