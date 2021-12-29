package publicadministration;

import data.AccredNumb;
import data.Nif;
import data.exceptions.WrongAccredNumbFormatException;
import data.exceptions.WrongDocPathFormatException;
import data.exceptions.WrongNifFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.MemberAccreditationDocTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberAccreditationDocTest implements MemberAccreditationDocTestInterface {

    MemberAccreditationDoc doc;
    Nif nif;
    AccredNumb accredNum;
    private final String nifValue = "44488877F";
    private final String accredNumValue = "444888777";


    @BeforeEach
    void setUp() throws WrongNifFormatException, WrongDocPathFormatException, WrongAccredNumbFormatException {
        nif = new Nif(nifValue);
        accredNum = new AccredNumb(accredNumValue);
        doc = new MemberAccreditationDoc(nif, accredNum);
    }

    @Test
    @Override
    public void getNifTest() {
        assertEquals(nif, doc.getNif());
    }

    @Test
    @Override
    public void getNumbAffilTest() {
        assertEquals(accredNum, doc.getNumbAffil());
    }
}
