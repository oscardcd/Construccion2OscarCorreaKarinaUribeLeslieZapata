package com.mycompany.clinicmanagement.infrastructure.repositoty;

import com.mycompany.clinicmanagement.infrastructure.adapters.*;
import com.mycompany.clinicmanagement.domain.models.Nurse;
import com.mycompany.clinicmanagement.domain.ports.NursePort;
import java.util.ArrayList;
import java.util.List;

public class NurseRepository implements NursePort {
    private final List<Nurse> nurses = new ArrayList<>();

@Override
    public Nurse save(Nurse nurse) {
        nurses.add(nurse);
        return nurse;
    }

@Override
    public Nurse findById(String nurseId) {
        return nurses.stream()
               .filter(n -> n.getNurseId().equals(nurseId))
               .findFirst()
               .orElse(null);
    }

@Override
    public List<Nurse> findAll() {
        return new ArrayList<>(nurses);
    }

@Override
    public List<Nurse> findBySpecialization(String specialization) {
        return nurses.stream()
                .filter(n -> n.getSpecialization().equalsIgnoreCase(specialization))
                .toList();
    }

@Override
    public void delete(String nurseId) {
        nurses.removeIf(n -> n.getNurseId().equals(nurseId));
    }
}
