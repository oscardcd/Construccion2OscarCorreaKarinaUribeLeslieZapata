package com.mycompany.clinicmanagement.adapter.in.builder;

import com.mycompany.clinicmanagement.domain.models.NurseVisit;
import com.mycompany.clinicmanagement.domain.models.nursing.NursingIntervention;
import com.mycompany.clinicmanagement.adapter.in.validator.NurseVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class NurseVisitBuilder {

    @Autowired
    private NurseVisitValidator nurseVisitValidator;

    public NurseVisit build(String patientId, String nurseId, String observations,
                           List<NursingIntervention> interventions) {

        NurseVisit visit = new NurseVisit();
        visit.setVisitId(generateVisitId());
        visit.setPatientId(nurseVisitValidator.patientIdValidator(patientId));
        visit.setNurseId(nurseVisitValidator.nurseIdValidator(nurseId));
        visit.setObservations(nurseVisitValidator.observationsValidator(observations));
        visit.setInterventions(interventions);
        visit.setVisitDate(LocalDateTime.now());

        return visit;
    }

    private String generateVisitId() {
        return "VIS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}