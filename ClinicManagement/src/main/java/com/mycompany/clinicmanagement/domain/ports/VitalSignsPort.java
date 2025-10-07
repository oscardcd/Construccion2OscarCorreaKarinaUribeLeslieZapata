package com.mycompany.clinicmanagement.domain.ports;

import com.mycompany.clinicmanagement.domain.models.VitalSigns;
import java.util.List;

public interface VitalSignsPort {
    VitalSigns save(VitalSigns vitalSigns);
    VitalSigns findById(String vitalId);
    List<VitalSigns> findByPatient(String patientId);
    List<VitalSigns> findAll();
    void delete(String vitalId);
}

