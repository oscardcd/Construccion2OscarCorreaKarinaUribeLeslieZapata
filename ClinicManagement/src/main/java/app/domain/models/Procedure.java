package app.domain.models;

public class Procedure {

    private String procedureId;
    private String name;
    private int repetitions;
    private String frequency;
    private double cost;
    private boolean requiresSpecialist;
    private String specialistType;

    public Procedure(String procedureId, String name, int repetitions, String frequency, double cost, boolean requiresSpecialist, String specialistType) {
        this.procedureId = procedureId;
        this.name = name;
        this.repetitions = repetitions;
        this.frequency = frequency;
        this.cost = cost;
        this.requiresSpecialist = requiresSpecialist;
        this.specialistType = specialistType;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isRequiresSpecialist() {
        return requiresSpecialist;
    }

    public void setRequiresSpecialist(boolean requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }

    public String getSpecialistType() {
        return specialistType;
    }

    public void setSpecialistType(String specialistType) {
        this.specialistType = specialistType;
    }
}
