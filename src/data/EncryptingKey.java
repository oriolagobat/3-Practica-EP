package data;

import java.math.BigInteger;

/**
 * Essential data classes for the optional part of the task
 */
public class EncryptingKey {
    private final BigInteger key;

    public EncryptingKey(BigInteger key) {
        checkEncryptingKey(key);

        this.key = key;
    }

    private void checkEncryptingKey(BigInteger key) {
        if (key == null) {
            throw new NullPointerException("La clau passada és null");
        }
    }

    public BigInteger getEncryptingKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncryptingKey number = (EncryptingKey) o;
        return key.equals(number.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "EncryptingKey{" + "clave de encriptación='" + key + '\'' + '}';
    }
}
