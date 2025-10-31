/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

//@Service
import app.domain.models.User;
import app.domain.models.enums.Role;
import app.domain.ports.UserPort;

public class CreateUser {

//	@Autowired
    private UserPort userPort;

    public void create(User user) throws Exception {
        if (userPort.findByDocument(user) != null) {
            throw new Exception("There is a person registered with this ID");
        }

        if (!user.getRole().equals(Role.ADMIN) && userPort.findByUserName(user) != null) {
            throw new Exception("There is already a person registered with that username");
        }
        userPort.save(user);
    }

}
