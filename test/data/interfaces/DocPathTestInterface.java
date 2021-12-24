package data.interfaces;

import data.DocPath;
import exceptions.WrongDocPathFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface DocPathTestInterface {
    @Test
    void getDocPathTest();

    @Test
    default void getNullPointerDocPathTest() {
        assertThrows(NullPointerException.class,
                () -> new DocPath(null));
    }

    @Test
    default void getWrongFormatPathException() {
        assertThrows(WrongDocPathFormatException.class,
                () -> {
                    String tooSmall = "";
                    new DocPath(tooSmall);
                });
    }
}