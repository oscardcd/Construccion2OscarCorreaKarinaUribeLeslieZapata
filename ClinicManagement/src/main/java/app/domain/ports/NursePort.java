/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.ports;

import app.domain.models.Nurse;

/**
 *
 * @author USUARIO
 */

import java.util.List;

public interface NursePort {

    Nurse findById(String id) throws Exception;

    Nurse findByEmail(String email) throws Exception;

    void save(Nurse nurse) throws Exception;

    void delete(String id) throws Exception;

    List<Nurse> findAll() throws Exception;
}
