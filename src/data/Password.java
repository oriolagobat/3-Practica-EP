package data;

import exceptions.WrongPasswordFormatException;

/**
 * Essential data classes
 */
final public class Password {
    // Represents a user's password
    private final String password;

    public Password(String pass) throws WrongPasswordFormatException {
        checkPassword(pass);

        this.password = pass;
    }

    private void checkPassword (String pass) throws WrongPasswordFormatException {
        int correctPasswordLength = 3;

        if (pass == null) throw new NullPointerException("El password és null");

        if (pass.length() != correctPasswordLength)
            throw new WrongPasswordFormatException("La longitud del password no és la correcta (3 dígits)");

        if (wrongPasswordFormat(pass))
            throw new WrongPasswordFormatException("El format del password no és el correcte. Recorda, tres dígits");
    }

    private boolean wrongPasswordFormat(String pass) {
        char[] password = pass.toCharArray();
        for (char c : password) {
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password pass = (Password) o;
        return password.equals(pass.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public String toString() {
        return "Password{" + "password ciudadano='" + password + '\'' + '}';
    }
}
