package app.domain.models;

import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Invoice {

    private String invoiceId;
    private Patient patient;
    private User doctor;
    private HealthInsurance healthInsurance;

    private List<MedicationOrder> medicationOrders;
    private List<ProcedureOrder> procedureOrders;
    private List<DiagnosticOrder> diagnosticOrders;

    private double totalAmount;
    private double copayment;

    public Invoice(String invoiceId, Patient patient, User doctor,
            HealthInsurance healthInsurance,
            List<MedicationOrder> medicationOrders,
            List<ProcedureOrder> procedureOrders,
            List<DiagnosticOrder> diagnosticOrders,
            double totalAmount, double copayment) {
        this.invoiceId = invoiceId;
        this.patient = patient;
        this.doctor = doctor;
        this.healthInsurance = healthInsurance;
        this.medicationOrders = medicationOrders;
        this.procedureOrders = procedureOrders;
        this.diagnosticOrders = diagnosticOrders;
        this.totalAmount = totalAmount;
        this.copayment = copayment;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public HealthInsurance getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(HealthInsurance healthInsurance) {
        this.healthInsurance = healthInsurance;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCopayment() {
        return copayment;
    }

    public void setCopayment(double copayment) {
        this.copayment = copayment;
    }

}
