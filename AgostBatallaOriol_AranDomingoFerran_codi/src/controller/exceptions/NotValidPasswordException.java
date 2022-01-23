package controller.exceptions;

public class NotValidPasswordException extends Exception {
    public NotValidPasswordException(String message) {
        super(message);
    }
}
