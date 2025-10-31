/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

/**
 *
 * @author USUARIO
 */
import app.domain.models.Nurse;
import app.domain.ports.NursePort;

import java.util.List;

public class NurseService {

    private final NursePort nursePort;

    public NurseService(NursePort nursePort) {
        this.nursePort = nursePort;
    }

    public void createNurse(Nurse nurse) throws Exception {
        if (nursePort.findById(nurse.getId()) != null) {
            throw new Exception("There is already a registered nurse with this card");
        }

        if (nursePort.findByEmail(nurse.getEmail()) != null) {
            throw new Exception("There is a registered nurse with this email address.");
        }

        nursePort.save(nurse);
    }

    public Nurse getNurseById(String id) throws Exception {
        return nursePort.findById(id);
    }

    public void deleteNurse(String id) throws Exception {
        nursePort.delete(id);
    }

    public List<Nurse> getAllNurses() throws Exception {
        return nursePort.findAll();
    }
}

