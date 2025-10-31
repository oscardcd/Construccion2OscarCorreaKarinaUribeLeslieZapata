/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.ports;

/**
 *
 * @author USUARIO
 */
import app.domain.models.MedicationOrder;
import app.domain.models.ProcedureOrder;
import app.domain.models.DiagnosticOrder;

import java.util.List;

public interface OrderPort {

    void saveMedicationOrder(MedicationOrder order) throws Exception;
    MedicationOrder findMedicationById(String orderId, long itemNumber) throws Exception;
    List<MedicationOrder> findAllMedicationOrders() throws Exception;

    void saveProcedureOrder(ProcedureOrder order) throws Exception;
    ProcedureOrder findProcedureById(String orderId, long itemNumber) throws Exception;
    List<ProcedureOrder> findAllProcedureOrders() throws Exception;

    void saveDiagnosticOrder(DiagnosticOrder order) throws Exception;
    DiagnosticOrder findDiagnosticById(String orderId, long itemNumber) throws Exception;
    List<DiagnosticOrder> findAllDiagnosticOrders() throws Exception;
}
