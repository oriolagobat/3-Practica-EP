package testInterfaces;

import data.DocPath;
import data.Nif;
import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface NifTestInterface {
    @Test
    void getNifTest();

    @Test
    default void getNullPointerNifTest() {
        assertThrows(NullPointerException.class,
                () -> new Nif(null));
    }

    @Test
    default void tooFewNumbersTest() {
        assertThrows(WrongNifFormatException.class,
                () -> {
                    String fewNumbers = "123A";
                    new Nif(fewNumbers);
                });
    }

    @Test
    default void tooManyNumbersTest() {
        assertThrows(WrongNifFormatException.class,
                () -> {
                    String manyNumbers = "123456789A";
                    new Nif(manyNumbers);
                });
    }

    @Test
    default void tooFewLettersTest() {
        assertThrows(WrongNifFormatException.class,
                () -> {
                    String fewLetters = "12345678";
                    new Nif(fewLetters);
                });
    }

    @Test
    default void tooManyLettersTest() {
        assertThrows(WrongNifFormatException.class,
                () -> {
                    String manyLetters = "12345678AB";
                    new Nif(manyLetters);
                });
    }

    @Test
    default void lowerCaseLettersTest() {
        assertThrows(WrongNifFormatException.class,
                () -> {
                    String lowerCaseLetters = "12345678a";
                    new Nif(lowerCaseLetters);
                });
    }
}
