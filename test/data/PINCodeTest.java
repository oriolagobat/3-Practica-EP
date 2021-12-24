package data;

import exceptions.WrongPINCodeFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.interfaces.PINCodeTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PINCodeTest implements PINCodeTestInterface {
    PINcode pinCode;

    @BeforeEach
    public void setUp() throws WrongPINCodeFormatException {
        String correctPINCode = "123";
        pinCode = new PINcode(correctPINCode);
    }

    @Test
    @Override
    public void getPINCodeTest() {
        String correctPINCode = "123";
        assertEquals(correctPINCode, pinCode.getPinCode());
    }
}
