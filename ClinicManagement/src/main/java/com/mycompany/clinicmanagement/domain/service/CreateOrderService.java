package com.mycompany.clinicmanagement.domain.service;

import com.mycompany.clinicmanagement.domain.models.Order;
import com.mycompany.clinicmanagement.domain.ports.OrderPort;
import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderService {
    private final OrderPort orderPort;
    
    public CreateOrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public Order create(String orderId, String patientId, String doctorId,
            LocalDateTime orderDate, String orderType, String priority,
            String status, List<String> medications, List<String> procedures,
            String observations) {
        
        Order order = new Order.Builder()
                .orderId(orderId)
                .patientId(patientId)
                .doctorId(doctorId)
                .orderDate(orderDate)
                .orderType(orderType)
                .priority(priority)
                .status(status)
                .medications(medications)
                .procedures(procedures)
                .observations(observations)
                .build();
        return orderPort.save(order);
    }

}
