package com.mycompany.clinicmanagement.infrastructure.repositoty;

import com.mycompany.clinicmanagement.domain.models.VitalSigns;
import com.mycompany.clinicmanagement.domain.ports.VitalSignsPort;
import java.util.ArrayList;
import java.util.List;

public class VitalSignsRepository implements VitalSignsPort {
    private final List<VitalSigns> vitalSignsList = new ArrayList<>();

@Override
    public VitalSigns save(VitalSigns vitalSigns) {
        vitalSignsList.add(vitalSigns);
        return vitalSigns;
    }

@Override
    public VitalSigns findById(String vitalId) {
        return vitalSignsList.stream()
                .filter(v -> v.getVitalId().equals(vitalId))
                .findFirst()
                .orElse(null);
    }

@Override
    public List<VitalSigns> findByPatient(String patientId) {
        return vitalSignsList.stream()
                .filter(v -> v.getPatientId().equals(patientId))
                .toList();
    }

@Override
    public List<VitalSigns> findAll() {
        return new ArrayList<>(vitalSignsList);
    }

@Override
    public void delete(String vitalId) {
        vitalSignsList.removeIf(v -> v.getVitalId().equals(vitalId));
    }
}
