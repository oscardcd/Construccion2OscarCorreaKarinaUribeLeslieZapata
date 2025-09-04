/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.ports;

import app.domain.models.User;

public interface UserPort {

    public User findByDocument(User user) throws Exception;

    public User findByUserName(User user) throws Exception;

    public void save(User user) throws Exception;
}
