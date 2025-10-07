
package com.mycompany.clinicmanagement.domain.models;


import java.time.LocalDateTime;

public class VitalSigns {
private String vitalId;
private String patientId;
private double temperature;
private int heartRate;
private int systolic;
private int diastolic;
private int respiratoryRate;
private LocalDateTime recordedAt;

private VitalSigns() {}

public String getVitalId() { return vitalId; }
public String getPatientId() { return patientId; }
public double getTemperature() { return temperature; }
public int getHeartRate() { return heartRate; }
public int getSystolic() { return systolic; }
public int getDiastolic() { return diastolic; }
public int getRespiratoryRate() { return respiratoryRate; }
public LocalDateTime getRecordedAt() { return recordedAt; }

public static class Builder {
    private final VitalSigns vitals;

    public Builder() { this.vitals = new VitalSigns(); }

    public Builder vitalId(String id) { vitals.vitalId = id; return this; }
    public Builder patientId(String id) { vitals.patientId = id; return this; }
    public Builder temperature(double temp) { vitals.temperature = temp; return this; }
    public Builder heartRate(int hr) { vitals.heartRate = hr; return this; }
    public Builder systolic(int s) { vitals.systolic = s; return this; }
    public Builder diastolic(int d) { vitals.diastolic = d; return this; }
    public Builder respiratoryRate(int rr) { vitals.respiratoryRate = rr; return this; }
    public Builder recordedAt(LocalDateTime date) { vitals.recordedAt = date; return this; }

    public VitalSigns build() {
        if (vitals.patientId == null || vitals.patientId.isBlank())
            throw new IllegalStateException("Patient ID is required");
        return vitals;
    }
}

}
