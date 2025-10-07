package com.mycompany.clinicmanagement.domain.models;

import java.time.LocalDateTime;

public class MedicalVisit {
private String visitId;
private String patientId;
private String doctorId;
private LocalDateTime visitDate;
private String notes;
private String orderId; // MedicalOrder
private String vitalId; // VitalSigns

private MedicalVisit() {}

public String getVisitId() { return visitId; }
public String getPatientId() { return patientId; }
public String getDoctorId() { return doctorId; }
public LocalDateTime getVisitDate() { return visitDate; }
public String getNotes() { return notes; }
public String getOrderId() { return orderId; }
public String getVitalId() { return vitalId; }

public static class Builder {
    private final MedicalVisit visit;

    public Builder() { this.visit = new MedicalVisit(); }

    public Builder visitId(String id) { visit.visitId = id; return this; }
    public Builder patientId(String id) { visit.patientId = id; return this; }
    public Builder doctorId(String id) { visit.doctorId = id; return this; }
    public Builder visitDate(LocalDateTime date) { visit.visitDate = date; return this; }
    public Builder notes(String notes) { visit.notes = notes; return this; }
    public Builder orderId(String orderId) { visit.orderId = orderId; return this; }
    public Builder vitalId(String vitalId) { visit.vitalId = vitalId; return this; }

    public MedicalVisit build() {
        if (visit.patientId == null || visit.patientId.isBlank())
            throw new IllegalStateException("Patient ID is required");
        if (visit.visitDate == null)
            throw new IllegalStateException("Visit date is required");
        return visit;
    }
}


}
