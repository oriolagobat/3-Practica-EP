package data.interfaces;

import data.EncryptingKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface EncryptingKeyTestInterface {
    @Test
    void getEncryptingKeyTest();

    @Test
    default void getNullPointerDecryptingKeyTest() {
        assertThrows(NullPointerException.class,
                () -> new EncryptingKey(null));
    }
}
