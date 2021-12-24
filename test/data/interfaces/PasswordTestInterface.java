package data.interfaces;

import data.Password;
import exceptions.WrongPasswordFormatException;
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
                    Password password = new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoDigitTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "contrasenya";
                    Password password = new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoCharTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "12345678";
                    Password password = new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoSpecialCharTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "contrasenya123";
                    Password password = new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNullTest() {
        assertThrows(NullPointerException.class,
                () -> {
                    Password password = new Password(null);
                });
    }

}