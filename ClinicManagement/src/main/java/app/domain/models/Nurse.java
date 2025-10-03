package app.domain.models;

import java.util.ArrayList;
import java.util.Objects;

public class Nurse {

    private String nurseId;
    private String firstName;
    private String lastName;
    private String licenseNumber; 

    public Nurse(String nurseId, String firstName, String lastName, String licenseNumber) {
        this.nurseId = nurseId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
    }

    // Registrar signos vitales como observación en la visita
    public void recordVitalSigns(MedicalVisit visit, double bloodPressure, double temperature, int pulse, double oxygenLevel) {
        String notes = "Signos vitales " +
                "Presión: " + bloodPressure +
                ", Temp: " + temperature +
                ", Pulso: " + pulse +
                ", Oxígeno: " + oxygenLevel;
        addObservation(visit, notes);
    }

    // Registrar observación general
    public void addObservation(MedicalVisit visit, String note) {
        if (visit.getDiagnosis() == null || visit.getDiagnosis().isEmpty()) {
            visit.setDiagnosis("Obs. enfermería: " + note);
        } else {
            visit.setDiagnosis(visit.getDiagnosis() + " | Obs. enfermería: " + note);
        }
    }

    // Aplicar un medicamento de la orden
    public void applyMedication(MedicalVisit visit, MedicationOrder order, String nurseNote) {
        if (visit.getMedicationOrders() == null) {
            visit.setMedicationOrders(new ArrayList<>());
        }
        visit.getMedicationOrders().add(order);

        addObservation(visit, "Medicamento aplicado: " +
                order.getMedicationName() +
                ", Dosis: " + order.getDosage() +
                ", Duración: " + order.getDuration() +
                " (" + nurseNote + ")");
    }

    // Ejecutar un procedimiento de la orden
    public void performProcedure(MedicalVisit visit, ProcedureOrder order, String nurseNote) {
        if (visit.getProcedureOrders() == null) {
            visit.setProcedureOrders(new ArrayList<>());
        }
        visit.getProcedureOrders().add(order);

        addObservation(visit, "Procedimiento realizado: " +
                order.getProcedureName() +
                ", Veces: " + order.getQuantity() +
                ", Frecuencia: " + order.getFrequency() +
                (order.isRequiresSpecialist() ? ", con especialista" : "") +
                " (" + nurseNote + ")");
    }

    // Registrar una ayuda diagnóstica aplicada
    public void registerDiagnostic(MedicalVisit visit, DiagnosticOrder order, String nurseNote) {
        if (visit.getDiagnosticOrders() == null) {
            visit.setDiagnosticOrders(new ArrayList<>());
        }
        visit.getDiagnosticOrders().add(order);

        addObservation(visit, "Examen aplicado: " +
                order.getExamName() +
                ", Cantidad: " + order.getQuantity() +
                (order.isRequiresSpecialist() ? ", con especialista" : "") +
                " (" + nurseNote + ")");
    }

    // Métodos auxiliares
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getNurseId() {
        return nurseId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nurse)) return false;
        Nurse nurse = (Nurse) o;
        return Objects.equals(licenseNumber, nurse.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id='" + nurseId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                '}';
    }
}
