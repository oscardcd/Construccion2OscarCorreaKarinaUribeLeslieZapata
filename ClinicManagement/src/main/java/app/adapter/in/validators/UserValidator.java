package app.adapter.in.validators;

//import org.springframework.stereotype.Component;
//@Component
public class UserValidator extends SimpleValidators {

    public String nameValidator(String value) throws Exception {
        return stringValidator("nombre de la persona", value);
    }

    public String userNameValidator(String value) throws Exception {
        return stringValidator("nombre del usuario", value);
    }

    public String passwordValidator(String value) throws Exception {
        return stringValidator("contrasela", value);
    }

    public long documentValidator(String value) throws Exception {
        return longValidator("el documento de la persona", value);
    }

    public int ageValidator(String value) throws Exception {
        return integerValidator("edad de la persona", value);
    }

}
