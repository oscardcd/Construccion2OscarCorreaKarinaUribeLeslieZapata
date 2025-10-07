package com.mycompany.clinicmanagement.infrastructure.adapters;

import com.mycompany.clinicmanagement.domain.models.MedicalVisit;
import com.mycompany.clinicmanagement.domain.ports.MedicalVisitPort;
import java.util.ArrayList;
import java.util.List;

public class MedicalVisitRepository implements MedicalVisitPort {
    private final List<MedicalVisit> visits = new ArrayList<>();

@Override
    public MedicalVisit save(MedicalVisit visit) {
        visits.add(visit);
        return visit;
    }

@Override
    public MedicalVisit findById(String visitId) {
        return visits.stream()
                .filter(v -> v.getVisitId().equals(visitId))
                .findFirst()
                .orElse(null);
    }

@Override
    public List<MedicalVisit> findByPatient(String patientId) {
        return visits.stream()
                .filter(v -> v.getPatientId().equals(patientId))
                .toList();
    }

@Override
    public List<MedicalVisit> findAll() {
        return new ArrayList<>(visits);
    }

@Override
    public void delete(String visitId) {
        visits.removeIf(v -> v.getVisitId().equals(visitId));
    }
}

