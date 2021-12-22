package data;

import exceptions.WrongPINCodeFormatException;
import org.junit.jupiter.api.BeforeEach;
import testInterfaces.PINCodeTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PINCodeTest implements PINCodeTestInterface {
    PINcode pinCode;

    @BeforeEach
    public void setUp() throws WrongPINCodeFormatException {
        String correctPINCode = "123";
        pinCode = new PINcode(correctPINCode);
    }

    @Override
    public void getPINCodeTest() {
        String correctPINCode = "123";
        assertEquals(correctPINCode, pinCode.getPinCode());
    }
}
