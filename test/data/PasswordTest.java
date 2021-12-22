package data;

import exceptions.WrongPasswordFormatException;
import org.junit.jupiter.api.BeforeEach;
import testInterfaces.PasswordTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest implements PasswordTestInterface {
    Password password;

    @BeforeEach
    public void setUp() throws WrongPasswordFormatException {
        String correctPassword = "123";
        password = new Password(correctPassword);
    }

    @Override
    public void getPasswordTest() {
        String correctPassword = "123";
        assertEquals(correctPassword, password.getPassword());
    }
}
