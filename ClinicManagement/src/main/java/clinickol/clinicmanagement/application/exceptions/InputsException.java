package clinickol.clinicmanagement.application.exceptions;

public class InputsException extends Exception {
    public InputsException(String message) {
        super(message);
    }

    public InputsException(String message, Throwable cause) {
        super(message, cause);
    }
}

