
package com.mycompany.clinicmanagement.adapter.in.builder;

import com.mycompany.clinicmanagement.domain.models.VitalSigns;
import com.mycompany.clinicmanagement.adapter.in.validator.VitalSignsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class VitalSignsBuilder {

    @Autowired
    private VitalSignsValidator vitalSignsValidator;

    public VitalSigns build(String patientId, String nurseId, String bloodPressure,
                           Double temperature, Integer pulse, Double oxygenLevel) {

        VitalSigns vitalSigns = new VitalSigns();
        vitalSigns.setVitalSignsId(generateVitalSignsId());
        vitalSigns.setPatientId(vitalSignsValidator.patientIdValidator(patientId));
        vitalSigns.setNurseId(vitalSignsValidator.nurseIdValidator(nurseId));
        vitalSigns.setBloodPressure(vitalSignsValidator.bloodPressureValidator(bloodPressure));
        vitalSigns.setTemperature(vitalSignsValidator.temperatureValidator(temperature));
        vitalSigns.setPulse(vitalSignsValidator.pulseValidator(pulse));
        vitalSigns.setOxygenLevel(vitalSignsValidator.oxygenLevelValidator(oxygenLevel));
        vitalSigns.setRecordDate(LocalDateTime.now());

        return vitalSigns;
    }

    private String generateVitalSignsId() {
        return "VIT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
