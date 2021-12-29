package publicadministration.interfaces;

import org.junit.jupiter.api.Test;
import publicadministration.QuotePeriod;
import publicadministration.QuotePeriodsColl;
import publicadministration.exceptions.DuplicatedQuotePeriodException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface QuotePeriodCollTestInterface {
    @Test
    default void getNullPointerTest() {
        assertThrows(NullPointerException.class,
                () -> {
                    QuotePeriodsColl quotePeriodsColl = new QuotePeriodsColl();
                    quotePeriodsColl.addQuotePeriod(null);
                });
    }

    @Test
    default void getDuplicatedQuotePeriodTest() {
        assertThrows(DuplicatedQuotePeriodException.class,
                () -> {
                    QuotePeriodsColl quotePeriodsColl = new QuotePeriodsColl();
                    QuotePeriod duplicateQuotePeriod = new QuotePeriod(new Date(), 0);
                    quotePeriodsColl.addQuotePeriod(duplicateQuotePeriod);

                    // We should get the exception here
                    quotePeriodsColl.addQuotePeriod(duplicateQuotePeriod);
                });
    }

    @Test
    void getQuotePeriodsCollTest();

    @Test
    void checkLengthTest();

    @Test
    void addAndCheckLengthTest() throws DuplicatedQuotePeriodException;

    @Test
    void checkOlderSortTest() throws DuplicatedQuotePeriodException;

    @Test
    void checkNewerSortTest() throws DuplicatedQuotePeriodException;
}