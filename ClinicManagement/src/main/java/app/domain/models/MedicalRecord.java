package app.domain.models;
import java.util.HashMap;

public class MedicalRecord {

    
    private String patientId;
    private HashMap<String, MedicalVisit> visits;

    
    public MedicalRecord(String patientId) {
        this.patientId = patientId;
        this.visits = new HashMap<>();
    }


    // Crea la historia clínica con la primera visita
    public void createHistory(String doctorId, String date, String reason, String symptoms, String diagnosis) {
        MedicalVisit visit = new MedicalVisit(date, doctorId, reason, symptoms, diagnosis);
        visits.put(date, visit);
    }

    // Agrega una nueva visita a la historia existente
    public void addVisit(String date, MedicalVisit visit) {
        visits.put(date, visit);
    }

    // Obtiene una visita específica por fecha
    public MedicalVisit getVisitByDate(String date) {
        return visits.get(date);
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
