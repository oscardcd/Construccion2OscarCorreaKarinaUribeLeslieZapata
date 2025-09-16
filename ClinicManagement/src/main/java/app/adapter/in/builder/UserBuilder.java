/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.models.User;

/**
 *
 * @author oscarcorrea
 */
//@Component
public class UserBuilder {

//	@Autowired
    private UserValidator userValidator;

    public UserBuilder build(String name, String document, String age, String userName, String password) throws Exception {
        User user = new User();
        user.setfullName(userValidator.nameValidator(name));
//        user.setDoc (userValidator.documentValidator(document));
//        user.setAge(userValidator.ageValidator(age));
//        user.setUserName(userValidator.userNameValidator(userName));
//        user.setPassword(userValidator.passwordValidator(password));
//        return user;
    }

}
