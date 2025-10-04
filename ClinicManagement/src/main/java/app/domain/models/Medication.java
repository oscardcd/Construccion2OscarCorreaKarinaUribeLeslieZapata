package app.domain.models;

public class Medication {
    private String medicationId;
    private String name;
    private String dosageForm;   
    private String strength;     
    private double unitCost;

    public Medication(String medicationId, String name, String dosageForm, String strength, double unitCost) {
        this.medicationId = medicationId;
        this.name = name;
        this.dosageForm = dosageForm;
        this.strength = strength;
        this.unitCost = unitCost;
    }

    // Getters y setters
    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
}
