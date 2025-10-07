package com.mycompany.clinicmanagement.domain.service;

import com.mycompany.clinicmanagement.domain.models.VitalSigns;
import com.mycompany.clinicmanagement.domain.ports.VitalSignsPort;
import java.time.LocalDateTime;

public class CreateVitalSignsService {
    private final VitalSignsPort vitalSignsPort;
    public CreateVitalSignsService(VitalSignsPort vitalSignsPort) {
        this.vitalSignsPort = vitalSignsPort;
    }

    public VitalSigns create(String vitalId, String patientId, double temperature,
            int heartRate, int systolic, int diastolic,
            int respiratoryRate, LocalDateTime recordedAt) {
        
        VitalSigns vitalSigns = new VitalSigns.Builder()
                .vitalId(vitalId)
                .patientId(patientId)
                .temperature(temperature)
                .heartRate(heartRate)
                .systolicPressure(systolic)
                .diastolicPressure(diastolic)
                .respiratoryRate(respiratoryRate)
                .recordedAt(recordedAt)
                .build();
        return vitalSignsPort.save(vitalSigns);
    }

}

