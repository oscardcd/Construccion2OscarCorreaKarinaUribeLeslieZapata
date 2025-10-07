package com.mycompany.clinicmanagement.adapter.in.builder;

import com.mycompany.clinicmanagement.domain.models.nursing.NursingIntervention;
import com.mycompany.clinicmanagement.adapter.in.validator.NursingInterventionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class NursingInterventionBuilder {

    @Autowired
    private NursingInterventionValidator nursingInterventionValidator;

    public NursingIntervention build(String patientId, String nurseId, String type,
                                    String description, String status) {

        NursingIntervention intervention = new NursingIntervention();
        intervention.setInterventionId(generateInterventionId());
        intervention.setPatientId(nursingInterventionValidator.patientIdValidator(patientId));
        intervention.setNurseId(nursingInterventionValidator.nurseIdValidator(nurseId));
        intervention.setType(nursingInterventionValidator.typeValidator(type));
        intervention.setDescription(nursingInterventionValidator.descriptionValidator(description));
        intervention.setStatus(nursingInterventionValidator.statusValidator(status));
        intervention.setAdministrationDate(LocalDateTime.now());

        return intervention;
    }

    private String generateInterventionId() {
        return "INT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
