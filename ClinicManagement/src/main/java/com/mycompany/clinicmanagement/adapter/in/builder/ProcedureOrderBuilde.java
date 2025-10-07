package com.mycompany.clinicmanagement.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.clinicmanagement.domain.models.ProcedureOrder;
import com.mycompany.clinicmanagement.adapter.in.validator.ProcedureOrderValidator;

@Component
public class ProcedureOrderBuilder {

    @Autowired
    private ProcedureOrderValidator validator;

    public ProcedureOrder build(String orderNumber, int itemNumber, String procedureName, 
                               int repetitionCount, String repetitionFrequency, double cost,
                               boolean requiresSpecialist, String specialtyId) throws Exception {
        ProcedureOrder order = new ProcedureOrder();
        order.setOrderNumber(validator.orderNumberValidator(orderNumber));
        order.setItemNumber(validator.itemNumberValidator(itemNumber));
        order.setProcedureName(validator.procedureNameValidator(procedureName));
        order.setRepetitionCount(validator.repetitionCountValidator(repetitionCount));
        order.setRepetitionFrequency(validator.repetitionFrequencyValidator(repetitionFrequency));
        order.setCost(validator.costValidator(cost));
        order.setRequiresSpecialist(requiresSpecialist);
        order.setSpecialtyId(specialtyId); 
       
        return order;
    }
}