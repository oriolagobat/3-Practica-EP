package data;

import data.interfaces.EncryptedDataTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class EncryptedDataTest implements EncryptedDataTestInterface {
    EncryptedData encryptedData;

    @BeforeEach
    public void setUp() {
        byte[] data = "Prova".getBytes(StandardCharsets.UTF_8);
        encryptedData = new EncryptedData(data);
    }

    @Test
    @Override
    public void getEncryptedDataTest() {
        byte[] data = "Prova".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(data, encryptedData.getEncryptedData());
    }
}
