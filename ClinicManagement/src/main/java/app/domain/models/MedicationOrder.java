package app.domain.models;

public class MedicationOrder {

    private String orderId;
    private String patientId;
    private String doctorId;
    private String creationDate;
    private long itemNumber;
    private String medicationName;
    private String dosage;
    private String duration;
    private double cost;

    public MedicationOrder(String orderId, String patientId, String doctorId,
            String creationDate, int itemNumber,
            String medicationName, String dosage,
            String duration, double cost) {
        this.orderId = orderId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.creationDate = creationDate;
        this.itemNumber = itemNumber;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.duration = duration;
        this.cost = cost;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
