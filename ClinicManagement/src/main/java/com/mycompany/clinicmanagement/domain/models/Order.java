import java.time.LocalDateTime;
import java.util.List;

public class OrderService {
    private String orderId;
    private String patientId;
    private String doctorId;
    private LocalDateTime orderDate;
    private String orderType;
    private String priority;
    private String status;
    private List<String> medications;
    private List<String> procedures;
    private String observations;

    // Constructor privado
    private Order() {}

    public static class Builder {
        private Order order;

        public Builder() {
            this.order = new Order();
        }

        public Builder orderId(String orderId) {
            order.orderId = orderId;
            return this;
        }

        public Builder patientId(String patientId) {
            order.patientId = patientId;
            return this;
        }

        public Builder doctorId(String doctorId) {
            order.doctorId = doctorId;
            return this;
        }

        public Builder orderDate(LocalDateTime orderDate) {
            order.orderDate = orderDate;
            return this;
        }

        public Builder orderType(String orderType) {
            order.orderType = orderType;
            return this;
        }

        public Builder priority(String priority) {
            order.priority = priority;
            return this;
        }

        public Builder status(String status) {
            order.status = status;
            return this;
        }

        public Builder medications(List<String> medications) {
            order.medications = medications;
            return this;
        }

        public Builder procedures(List<String> procedures) {
            order.procedures = procedures;
            return this;
        }

        public Builder observations(String observations) {
            order.observations = observations;
            return this;
        }

        public OrderService build() {
            if (order.orderId == null || order.orderId.trim().isEmpty()) {
                throw new IllegalStateException("Order ID is required");
            }
            if (order.patientId == null || order.patientId.trim().isEmpty()) {
                throw new IllegalStateException("Patient ID is required");
            }
            return order;
        }
    }

    public String getOrderId() { return orderId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public String getOrderType() { return orderType; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public List<String> getMedications() { return medications; }
    public List<String> getProcedures() { return procedures; }
    public String getObservations() { return observations; }
}