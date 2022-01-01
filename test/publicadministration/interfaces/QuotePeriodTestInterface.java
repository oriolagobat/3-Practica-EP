package publicadministration.interfaces;

import org.junit.jupiter.api.Test;
import publicadministration.QuotePeriod;
import publicadministration.exceptions.WrongQuotePeriodFormatException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface QuotePeriodTestInterface {
    @Test
    default void getNullPointerDateTest() {
        assertThrows(NullPointerException.class,
                () -> new QuotePeriod(null, 6));
    }

    @Test
    default void getWrongFormatTestDate() {
        assertThrows(WrongQuotePeriodFormatException.class,
                () -> new QuotePeriod(new Date(), 6));
    }

    @Test
    void getInitDayTest();

    @Test
    void getNumDaysTest();
}