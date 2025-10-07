package com.mycompany.clinicmanagement.application.usecases;
 

import com.mycompany.clinicmanagement.domain.models.VitalSigns;
import com.mycompany.clinicmanagement.domain.ports.VitalSignsPort;
import java.time.LocalDateTime;

public class RecordVitalSignsUseCase {
    private final VitalSignsPort vitalSignsPort;

    public RecordVitalSignsUseCase(VitalSignsPort vitalSignsPort) {
        this.vitalSignsPort = vitalSignsPort;
    }
    
    public VitalSigns record(String vitalId, String patientId, double temperature,
            int heartRate, int systolic, int diastolic,
            int respiratoryRate, LocalDateTime recordedAt) {
        
        if (temperature < 30 || temperature > 45)
            throw new IllegalArgumentException("Temperature out of range");
        if (heartRate < 30 || heartRate > 200)
            throw new IllegalArgumentException("Heart rate out of range");
        
        VitalSigns vital = new VitalSigns.Builder()
                .vitalId(vitalId)
                .patientId(patientId)
                .temperature(temperature)
                .heartRate(heartRate)
                .systolicPressure(systolic)
                .diastolicPressure(diastolic)
                .respiratoryRate(respiratoryRate)
                .recordedAt(recordedAt)
                .build();
        return vitalSignsPort.save(vital);
    }

}

