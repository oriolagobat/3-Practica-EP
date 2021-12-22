package data;

import exceptions.WrongPINCodeFormatException;

/**
 * Essential data classes
 */
final public class PINcode {
    // Represents a number that helps with autentication
    private final String pinCode;

    public PINcode(String pin) throws WrongPINCodeFormatException {
        checkPINCode(pin);

        this.pinCode = pin;
    }

    private void checkPINCode(String pin) throws WrongPINCodeFormatException {
        int correctPINCodeLength = 3;

        if (pin == null) throw new NullPointerException("El PIN és null");

        if (pin.length() != correctPINCodeLength)
            throw new WrongPINCodeFormatException("La longitud del PIN no és la correcta (3 dígits)");

        if (wrongPINCodeFormat(pin))
            throw new WrongPINCodeFormatException("El format del PIN no és el correcte. Recorda, tres dígits");
    }

    private boolean wrongPINCodeFormat(String pin) {
        char[] password = pin.toCharArray();
        for (char c : password) {
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public String getPinCode() {
        return pinCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        data.PINcode code = (data.PINcode) o;
        return pinCode.equals(code.pinCode);
    }

    @Override
    public int hashCode() {
        return pinCode.hashCode();
    }

    @Override
    public String toString() {
        return "PIN code{" + "PIN ciudadano='" + pinCode + '\'' + '}';
    }
}
