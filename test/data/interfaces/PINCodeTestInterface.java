package data.interfaces;

import data.PINcode;
import data.exceptions.WrongPINCodeFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface PINCodeTestInterface {
    @Test
    void getPINCodeTest();

    @Test
    default void getNullPointerPINCodeTest() {
        assertThrows(NullPointerException.class,
                () -> new PINcode(null));
    }

    @Test
    default void getSmallPinException() {
        assertThrows(WrongPINCodeFormatException.class,
                () -> {
                    String smallPassword = "12";
                    new PINcode(smallPassword);
                });
    }

    @Test
    default void getBigPinException() {
        assertThrows(WrongPINCodeFormatException.class,
                () -> {
                    String bigPassword = "12";
                    new PINcode(bigPassword);
                });
    }

    @Test
    default void getDigitsException() {
        assertThrows(WrongPINCodeFormatException.class,
                () -> {
                    String incorrectDigits = "12a";
                    new PINcode(incorrectDigits);
                });
    }
}
