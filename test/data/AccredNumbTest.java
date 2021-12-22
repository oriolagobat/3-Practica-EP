package data;

import exceptions.WrongAccredNumbFormatException;
import org.junit.jupiter.api.BeforeEach;
import testInterfaces.AccredNumbTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccredNumbTest implements AccredNumbTestInterface {
    AccredNumb accred_num;

    @BeforeEach
    public void setUp() throws WrongAccredNumbFormatException {
        String correct_number = "123456789";
        accred_num = new AccredNumb(correct_number);
    }

    @Override
    public void getAccredNumTest() {
        String correct_number = "123456789";
        assertEquals(correct_number, accred_num.getAccredNum());
    }
}
