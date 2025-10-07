package com.mycompany.clinicmanagement.application.usecases;


import com.mycompany.clinicmanagement.domain.models.Order;
import com.mycompany.clinicmanagement.domain.ports.OrderPort;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CreateOrderUseCase {
    private final OrderPort orderPort;

    public CreateOrderUseCase(OrderPort orderPort) {
        this.orderPort = orderPort;
    }
    
    public Order create(String orderId, String patientId, String doctorId,
            LocalDateTime orderDate, String type, String priority,
            String status, List<String> medications, List<String> procedures,
            String observations) {
        Optional<Order> existing = orderPort.findAll().stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst();
        if (existing.isPresent())
            throw new IllegalArgumentException("Order already exists with ID: " + orderId);
        
        Order order = new Order.Builder()
                .orderId(orderId)
                .patientId(patientId)
                .doctorId(doctorId)
                .orderDate(orderDate)
                .orderType(type)
                .priority(priority)
                .status(status)
                .medications(medications)
                .procedures(procedures)
                .observations(observations)
                .build();
        return orderPort.save(order);
    }

}

