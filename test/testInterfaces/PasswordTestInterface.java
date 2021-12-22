package testInterfaces;

import data.Password;
import exceptions.WrongPasswordFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface PasswordTestInterface {
    @Test
    void getPasswordTest();

    @Test
    default void getNullPointerPasswordTest() {
        assertThrows(NullPointerException.class,
                () -> new Password(null));
    }

    @Test
    default void getSmallPassException() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String smallPassword = "12";
                    new Password(smallPassword);
                });
    }

    @Test
    default void getBigPassException() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String bigPassword = "12";
                    new Password(bigPassword);
                });
    }

    @Test
    default void getDigitsException() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String incorrectDigits = "12a";
                    new Password(incorrectDigits);
                });
    }
}
