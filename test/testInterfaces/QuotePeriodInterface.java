package testInterfaces;

import org.junit.jupiter.api.Test;
import publicadministration.QuotePeriod;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface QuotePeriodInterface {
    @Test
    void getInitDayTest();

    @Test
    void getNumDaysTest();

    @Test
    default void getNullPointerDateTest() {
        assertThrows(NullPointerException.class,
                () -> new QuotePeriod(null, 0));
    }
}
