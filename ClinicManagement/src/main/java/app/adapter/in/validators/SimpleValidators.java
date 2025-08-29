/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter.in.validators;

/**
 *
 * @author oscarcorrea
 */
public abstract class SimpleValidators {

    public String stringValidator(String value, String element) throws Exception {
        if (value == null || value.equals("")) {
            throw new Exception(element + "no puede tener un valor vacio o nulo");
        }
        return value;
    }

    public int integerValidator(String value, String element) throws Exception {
        stringValidator(element, value);

//        try {
//
//            if (value == null || value.equals("")) {
//                throw new Exception(element + "no puede tener un valor vacio o nulo");
//            }
//            return value;
//        } catch Exception ()
//                     {
//            }
//        }
        return Integer.parseInt(value);
    }
}
