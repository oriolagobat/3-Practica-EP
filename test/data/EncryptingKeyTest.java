package data;

import data.interfaces.EncryptingKeyTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptingKeyTest implements EncryptingKeyTestInterface {
    EncryptingKey encryptingKey;

    @BeforeEach
    public void setUp() {
        BigInteger key = new BigInteger("123456789");
        encryptingKey = new EncryptingKey(key);
    }

    @Test
    @Override
    public void getEncryptingKeyTest() {
        BigInteger key = new BigInteger("123456789");
        assertEquals(key, encryptingKey.getEncryptingKey());
    }
}
