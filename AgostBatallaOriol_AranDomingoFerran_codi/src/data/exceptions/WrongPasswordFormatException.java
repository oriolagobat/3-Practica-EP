package data.exceptions;

public class WrongPasswordFormatException extends Exception {
    public WrongPasswordFormatException(String message) {
        super(message);
    }
}
