package publicadministration.interfaces;

import org.junit.jupiter.api.Test;
import publicadministration.QuotePeriod;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface QuotePeriodTestInterface {
    @Test
    default void getNullPointerDateTest() {
        assertThrows(NullPointerException.class,
                () -> new QuotePeriod(null, 0));
    }

    @Test
    void getInitDayTest();

    @Test
    void getNumDaysTest();
}