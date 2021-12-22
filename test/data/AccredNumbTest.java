package data;

import exceptions.WrongAccredNumbFormatException;
import org.junit.jupiter.api.BeforeEach;
import testInterfaces.AccredNumbTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccredNumbTest implements AccredNumbTestInterface {
    AccredNumb accredNum;

    @BeforeEach
    public void setUp() throws WrongAccredNumbFormatException {
        String correctNumber = "123456789";
        accredNum = new AccredNumb(correctNumber);
    }

    @Override
    public void getAccredNumTest() {
        String correctNumber = "123456789";
        assertEquals(correctNumber, accredNum.getAccredNum());
    }
}
