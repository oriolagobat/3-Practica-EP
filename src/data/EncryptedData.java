package data;

import java.util.Arrays;

public class EncryptedData {
    private final byte[] data;

    public EncryptedData(byte[] data) {
        checkEncryptedData(data);

        this.data = data;
    }

    private void checkEncryptedData(byte[] data) {
        if (data == null) {
            throw new NullPointerException("Les dades passades són null");
        }
    }

    public byte[] getEncryptedData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncryptedData number = (EncryptedData) o;
        return Arrays.equals(data, number.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return "EncryptedData{" + "datos encriptados='" + Arrays.toString(data) + '\'' + '}';
    }
}
