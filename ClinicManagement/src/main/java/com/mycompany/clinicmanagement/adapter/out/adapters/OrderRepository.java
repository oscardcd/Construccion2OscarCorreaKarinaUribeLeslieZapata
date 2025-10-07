package com.mycompany.clinicmanagement.infrastructure.adapters;


import com.mycompany.clinicmanagement.domain.models.Order;
import com.mycompany.clinicmanagement.domain.ports.OrderPort;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements OrderPort {
    private final List<Order> orders = new ArrayList<>();

@Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

@Override
    public Order findById(String orderId) {
        return orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

@Override
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

@Override
    public List<Order> findByPatient(String patientId) {
        return orders.stream()
                .filter(o -> o.getPatientId().equals(patientId))
                .toList();
    }

@Override
    public void delete(String orderId) {
        orders.removeIf(o -> o.getOrderId().equals(orderId));
    }
}
