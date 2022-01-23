package data.interfaces;

import data.EncryptedData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface EncryptedDataTestInterface {
    @Test
    void getEncryptedDataTest();

    @Test
    default void getNullPointerEncryptedDataTest() {
        assertThrows(NullPointerException.class,
                () -> new EncryptedData(null));
    }
}
