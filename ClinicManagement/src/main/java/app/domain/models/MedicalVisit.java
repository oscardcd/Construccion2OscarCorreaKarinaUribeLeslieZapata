package app.domain.models;

import java.util.ArrayList;
import java.util.List;

public class MedicalVisit {

    
    private String date;
    private String doctorId;
    private String reasonForVisit;
    private String symptoms;
    private String diagnosis;

    private List<MedicationOrder> medicationOrders;
    private List<ProcedureOrder> procedureOrders;
    private List<DiagnosticOrder> diagnosticOrders;

    

    // Constructor listas cargadas
    public MedicalVisit(String date, String doctorId, String reasonForVisit,
                        String symptoms, String diagnosis,
                        List<MedicationOrder> medicationOrders,
                        List<ProcedureOrder> procedureOrders,
                        List<DiagnosticOrder> diagnosticOrders) {
        this.date = date;
        this.doctorId = doctorId;
        this.reasonForVisit = reasonForVisit;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medicationOrders = medicationOrders;
        this.procedureOrders = procedureOrders;
        this.diagnosticOrders = diagnosticOrders;
    }

    // Constructor simplificado 
    public MedicalVisit(String date, String doctorId, String reasonForVisit,
                        String symptoms, String diagnosis) {
        this.date = date;
        this.doctorId = doctorId;
        this.reasonForVisit = reasonForVisit;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medicationOrders = new ArrayList<>();
        this.procedureOrders = new ArrayList<>();
        this.diagnosticOrders = new ArrayList<>();
    }

    
    //Métodos
    public void addMedicationOrder(MedicationOrder order) {
        this.medicationOrders.add(order);
    }

    public void addProcedureOrder(ProcedureOrder order) {
        this.procedureOrders.add(order);
    }

    public void addDiagnosticOrder(DiagnosticOrder order) {
        this.diagnosticOrders.add(order);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<MedicationOrder> getMedicationOrders() {
        return medicationOrders;
    }

    public void setMedicationOrders(List<MedicationOrder> medicationOrders) {
        this.medicationOrders = medicationOrders;
    }

    public List<ProcedureOrder> getProcedureOrders() {
        return procedureOrders;
    }

    public void setProcedureOrders(List<ProcedureOrder> procedureOrders) {
        this.procedureOrders = procedureOrders;
    }

    public List<DiagnosticOrder> getDiagnosticOrders() {
        return diagnosticOrders;
    }

    public void setDiagnosticOrders(List<DiagnosticOrder> diagnosticOrders) {
        this.diagnosticOrders = diagnosticOrders;
    }
}
