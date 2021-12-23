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

    private void checkPassword(String pass) throws WrongPasswordFormatException {
        if (pass == null)
            throw new NullPointerException("El valor de la contrasenya és nul");
        else if (pass.length() < 4)
            throw new WrongPasswordFormatException("La llargada de la contrasenya és inferior al mínim");
        else if (!passwordHasDigit(pass))
            throw new WrongPasswordFormatException("La contrasenya a de contenir com a mínim un dígit");
        else if (!passwordHasChar(pass))
            throw new WrongPasswordFormatException("La contrasenya a de contenir com a mínim un caràcter");
        else if (!passwordHasSpecialChar(pass))
            throw new WrongPasswordFormatException("La contrasenya a de contenir com a mínim un caràcter especial");
    }

    private boolean passwordHasDigit(String pass){
        char[] passwd = pass.toCharArray();
        for(char c : passwd){
            if(Character.isDigit(c))
                return true;
        }
        return false;
    }

    private boolean passwordHasChar(String pass){
        char[] passwd = pass.toCharArray();
        for(char c : passwd){
            if(Character.isAlphabetic(c))
                return true;
        }
        return false;
    }

    private boolean passwordHasSpecialChar(String pass){
        char[] passwd = pass.toCharArray();
        for(char c : passwd){
            if(!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isSpaceChar(c))
                return true;
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
