package data;

import exceptions.WrongPasswordFormatException;
import org.junit.jupiter.api.BeforeEach;
import testInterfaces.PasswordTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest implements PasswordTestInterface {
    Password password;

    @BeforeEach
    public void setUp() throws WrongPasswordFormatException {
        password = new Password("$contrasenya123");
    }

    @Override
    public void getPasswordTest() {
        assertEquals("$contrasenya123", password.getPassword());
    }
}
