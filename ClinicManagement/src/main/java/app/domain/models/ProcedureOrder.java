package app.domain.models;


public class ProcedureOrder {

    private String orderId;
    private String patientId;
    private String doctorId;
    private String creationDate;

    private long itemNumber;
    private String procedureName;
    private long quantity;
    private String frequency;
    private boolean requiresSpecialist;
    private String specialistId;
    private double cost;

    public ProcedureOrder(String orderId, String patientId, String doctorId,
            String creationDate, int itemNumber, String procedureName,
            int quantity, String frequency, boolean requiresSpecialist,
            String specialistId, double cost) {
        this.orderId = orderId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.creationDate = creationDate;
        this.itemNumber = itemNumber;
        this.procedureName = procedureName;
        this.quantity = quantity;
        this.frequency = frequency;
        this.requiresSpecialist = requiresSpecialist;
        this.specialistId = specialistId;
        this.cost = cost;
    }

    public long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isRequiresSpecialist() {
        return requiresSpecialist;
    }

    public void setRequiresSpecialist(boolean requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
