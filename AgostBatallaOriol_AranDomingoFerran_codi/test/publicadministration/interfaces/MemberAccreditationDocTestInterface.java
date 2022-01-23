package publicadministration.interfaces;

import data.AccredNumb;
import data.Nif;
import org.junit.jupiter.api.Test;
import publicadministration.MemberAccreditationDoc;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface MemberAccreditationDocTestInterface {
    @Test
    default void getNullPointerNifTest() {
        assertThrows(NullPointerException.class,
                () -> new MemberAccreditationDoc(null, new AccredNumb("123546789")));
    }

    @Test
    default void getNullPointerAccredNumbTest() {
        assertThrows(NullPointerException.class,
                () -> new MemberAccreditationDoc(new Nif("12345678A"), null));
    }

    @Test
    void getNifTest();

    @Test
    void getNumbAffilTest();
}
