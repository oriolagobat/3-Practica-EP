package exceptions;

public class WrongPINCodeFormatException extends Exception {
    public WrongPINCodeFormatException(String message) {
        super(message);
    }
}
