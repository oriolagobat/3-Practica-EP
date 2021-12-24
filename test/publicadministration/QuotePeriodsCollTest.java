package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.Interfaces.QuotePeriodCollTestInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuotePeriodsCollTest implements QuotePeriodCollTestInterface {
    QuotePeriodsColl quotePeriodsColl;
    Date date;  // Common date to test

    @BeforeEach
    public void setUp() {
        quotePeriodsColl = new QuotePeriodsColl();

        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);
        date = cal.getTime();
        int qPDays = 0;

        QuotePeriod qP = new QuotePeriod(date, qPDays);

        for (int i = 0; i < 5; i++) {  // Add five quotePeriods to Test
            quotePeriodsColl.addQuotePeriod(qP);
        }
    }

    @Test
    @Override
    public void getQuotePeriodsCollTest() {
        ArrayList<QuotePeriod> correctQPC = new ArrayList<>();

        int qPDays = 0;
        QuotePeriod qP = new QuotePeriod(date, qPDays);

        for (int i = 0; i < 5; i++) {  // Add five quotePeriods to Test
            correctQPC.add(qP);
            correctQPC.sort(Comparator.comparing(QuotePeriod::getInitDay));  // Sorts array list by date
        }

        assertEquals(correctQPC, quotePeriodsColl.getQuotePeriodsCollection());
    }

    @Test
    @Override
    public void checkLengthTest() {
        int currentLength = 5;
        assertEquals(currentLength, quotePeriodsColl.getQuotePeriodsCollection().size());
    }

    @Test
    @Override
    public void addAndCheckLengthTest() {
        int correctLength = 5 + 1;
        int qPDays = 0;
        QuotePeriod qpDummy = new QuotePeriod(date, qPDays);
        quotePeriodsColl.addQuotePeriod(qpDummy);

        assertEquals(correctLength, quotePeriodsColl.getQuotePeriodsCollection().size());
    }

    @Test
    @Override
    public void checkSortTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(1950, Calendar.DECEMBER, 25);  // Create older quote period
        Date qPDate = cal.getTime();
        int qPDays = 0;
        QuotePeriod olderQP = new QuotePeriod(qPDate, qPDays);

        quotePeriodsColl.addQuotePeriod(olderQP);

        // Check if last quote period in the array list is the newer one
        assertEquals(olderQP, quotePeriodsColl.getQuotePeriodsCollection().get(0));
    }
}
