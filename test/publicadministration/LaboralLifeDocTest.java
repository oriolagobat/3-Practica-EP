package publicadministration;

import data.Nif;
import data.exceptions.WrongNifFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.LaboralLifeDocTestInterface;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaboralLifeDocTest implements LaboralLifeDocTestInterface {

    LaboralLifeDoc doc;
    Nif nif;
    QuotePeriodsColl qPds;
    private final String nifValue = "44488877F";

    @BeforeEach
    void setUp() throws WrongNifFormatException, WrongDocPathFormatException {
        nif = new Nif(nifValue);
        qPds = new QuotePeriodsColl();
        doc = new LaboralLifeDoc(nif, qPds);
    }

    @Test
    @Override
    public void getNifTest() {
        assertEquals(nifValue, doc.getNif().getNif());
    }

    @Test
    @Override
    public void getQuotePdsTest() {
        assertEquals(new ArrayList<>(),doc.getQuotePds().getQuotePeriodsCollection());
    }
}
