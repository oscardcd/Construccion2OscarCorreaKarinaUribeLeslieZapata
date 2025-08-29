package app.domain.models;

public class DiagnosticOrder {

    private String orderId;
    private String patientId;
    private String doctorId;
    private String creationDate;
    private long itemNumber;
    private String examName;
    private long quantity;
    private double cost;
    private boolean requiresSpecialist;
    private String specialistId;

    public DiagnosticOrder(String orderId, String patientId, String doctorId,
            String creationDate, int itemNumber,
            String examName, int quantity, double cost,
            boolean requiresSpecialist, String specialistId) {
        this.orderId = orderId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.creationDate = creationDate;
        this.itemNumber = itemNumber;
        this.examName = examName;
        this.quantity = quantity;
        this.cost = cost;
        this.requiresSpecialist = requiresSpecialist;
        this.specialistId = specialistId;
    }

    public long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }
}
