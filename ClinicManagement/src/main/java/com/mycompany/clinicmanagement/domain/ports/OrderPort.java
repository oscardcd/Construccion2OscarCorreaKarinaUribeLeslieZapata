package com.mycompany.clinicmanagement.domain.ports;

import com.mycompany.clinicmanagement.domain.models.Order;
import java.util.List;

public interface OrderPort {
    Order save(Order order);
    Order findById(String orderId);
    List<Order> findAll();
    List<Order> findByPatient(String patientId);
    void delete(String orderId);
}
