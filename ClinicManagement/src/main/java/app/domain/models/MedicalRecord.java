package app.domain.models;

import java.util.HashMap;


public class MedicalRecord {

    private String patientId;
    private HashMap<String, MedicalVisit> visits;

    public MedicalRecord(String patientId) {
        this.patientId = patientId;
        this.visits = new HashMap<>();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public HashMap<String, MedicalVisit> getVisits() {
        return visits;
    }

    public void setVisits(HashMap<String, MedicalVisit> visits) {
        this.visits = visits;
    }
}
