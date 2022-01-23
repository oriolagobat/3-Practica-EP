package data.interfaces;

import data.Password;
import data.exceptions.WrongPasswordFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface PasswordTestInterface {

    @Test
    void getPasswordTest();

    @Test
    default void wrongPasswordTooShortTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "123";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoDigitTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "contrasenya";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoCharTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "12345678";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoSpecialCharTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "contrasenya123";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNullTest() {
        assertThrows(NullPointerException.class,
                () -> new Password(null));
    }
}