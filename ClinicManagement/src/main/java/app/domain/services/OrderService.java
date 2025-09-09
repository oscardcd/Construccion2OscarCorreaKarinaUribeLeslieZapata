/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

/**
 *
 * @author USUARIO
 */
import app.domain.models.MedicationOrder;
import app.domain.models.ProcedureOrder;
import app.domain.models.DiagnosticOrder;
import app.domain.ports.OrderPort;

import java.util.List;

public class OrderService {

    private final OrderPort orderPort;

    public OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public void createMedicationOrder(MedicationOrder order) throws Exception {

        if (orderPort.findMedicationById(order.getOrderId(), order.getItemNumber()) != null) {
            throw new Exception("A medication with this order number and item already exists");
        }
        orderPort.saveMedicationOrder(order);
    }

    public List<MedicationOrder> getAllMedicationOrders() throws Exception {
        return orderPort.findAllMedicationOrders();
    }

    public void createProcedureOrder(ProcedureOrder order) throws Exception {
        if (orderPort.findProcedureById(order.getOrderId(), order.getItemNumber()) != null) {
            throw new Exception("A procedure with this order number and item already exists");
        }
        orderPort.saveProcedureOrder(order);
    }

    public List<ProcedureOrder> getAllProcedureOrders() throws Exception {
        return orderPort.findAllProcedureOrders();
    }

    public void createDiagnosticOrder(DiagnosticOrder order) throws Exception {
        if (orderPort.findDiagnosticById(order.getOrderId(), order.getItemNumber()) != null) {
            throw new Exception("A diagnostic aid with this order number and item already exists.");
        }


        if (order.isRequiresSpecialist() && order.getSpecialistId() == null) {
            throw new Exception("If diagnostic help requires a specialist, you must indicate the type");
        }

        orderPort.saveDiagnosticOrder(order);
    }

    public List<DiagnosticOrder> getAllDiagnosticOrders() throws Exception {
        return orderPort.findAllDiagnosticOrders();
    }
}