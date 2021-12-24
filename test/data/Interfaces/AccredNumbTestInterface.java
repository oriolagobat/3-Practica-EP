package data.Interfaces;

import data.AccredNumb;
import exceptions.WrongAccredNumbFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface AccredNumbTestInterface {
    @Test
    void getAccredNumTest();

    @Test
    default void getNullPointerAccredNumbTest() {
        assertThrows(NullPointerException.class,
                () -> new AccredNumb(null));
    }

    @Test
    default void getSmallLengthException() {
        assertThrows(WrongAccredNumbFormatException.class,
                () -> {
                    String smallNumber = "12345";
                    new AccredNumb(smallNumber);
                });
    }

    @Test
    default void getBigLengthException() {
        assertThrows(WrongAccredNumbFormatException.class,
                () -> {
                    String bigNumber = "1234567890123456";
                    new AccredNumb(bigNumber);
                });
    }

    @Test
    default void getLettersException() {
        assertThrows(WrongAccredNumbFormatException.class,
                () -> {
                    String lettersInNumber = "123x456";
                    new AccredNumb(lettersInNumber);
                });
    }
}
