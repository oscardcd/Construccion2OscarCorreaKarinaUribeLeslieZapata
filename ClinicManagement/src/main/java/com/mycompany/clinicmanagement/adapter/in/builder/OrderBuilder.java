package com.mycompany.clinicmanagement.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.clinicmanagement.domain.models.Order;
import com.mycompany.clinicmanagement.adapter.in.validator.OrderValidator;
import java.time.LocalDate;

@Component
public class OrderBuilder {

    @Autowired
    private OrderValidator orderValidator;

    public Order build(String orderNumber, String patientId, String doctorId,
                      LocalDate creationDate, String orderType, String orderStatus) throws Exception {
        
        Order order = new Order();
        order.setOrderNumber(orderValidator.orderNumberValidator(orderNumber));
        order.setPatientId(orderValidator.patientIdValidator(patientId));
        order.setDoctorId(orderValidator.doctorIdValidator(doctorId));
        order.setCreationDate(orderValidator.creationDateValidator(creationDate));
        order.setOrderType(orderValidator.orderTypeValidator(orderType));
        order.setOrderStatus(orderValidator.orderStatusValidator(orderStatus));
        
        return order;
    }
}