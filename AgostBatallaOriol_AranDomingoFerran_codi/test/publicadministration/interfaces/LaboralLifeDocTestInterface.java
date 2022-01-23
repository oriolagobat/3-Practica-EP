package publicadministration.interfaces;

import data.Nif;
import org.junit.jupiter.api.Test;
import publicadministration.LaboralLifeDoc;
import publicadministration.QuotePeriodsColl;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface LaboralLifeDocTestInterface {
    @Test
    default void getNullPointerNifTest() {
        assertThrows(NullPointerException.class,
                () -> new LaboralLifeDoc(null, new QuotePeriodsColl()));
    }

    @Test
    default void getNullPointerQpdcTest() {
        assertThrows(NullPointerException.class,
                () -> new LaboralLifeDoc(new Nif("12345678A"), null));
    }

    @Test
    void getNifTest();

    @Test
    void getQuotePdsTest();
}
