package data;

import org.junit.jupiter.api.BeforeEach;
import publicadminstration.QuotePeriod;
import publicadminstration.QuotePeriodsColl;
import testInterfaces.QuotePeriodCollInterface;

import java.util.Calendar;
import java.util.Date;

public class QuotePeriodsCollTest implements QuotePeriodCollInterface {
    QuotePeriodsColl quotePeriodsColl;

    @BeforeEach
    public void setUp() {
        quotePeriodsColl = new QuotePeriodsColl();

        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);
        Date qPDate = cal.getTime();
        int qPDays = 0;
        QuotePeriod quotePeriod = new QuotePeriod(qPDate, qPDays);

        for (int i = 0; i < 5; i++) {  // Add five quotePeriods to Test
            quotePeriodsColl.addQuotePeriod(quotePeriod);
        }
    }

    @Override
    public void getQuotePeriodsCollTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);
        Date qPDate = cal.getTime();
        int qPDays = 0;
        QuotePeriod quotePeriod = new QuotePeriod(qPDate, qPDays);

        for (int i = 0; i < 5; i++) {  // Add five quotePeriods to Test
            quotePeriodsColl.addQuotePeriod(quotePeriod);
        }
    }

    @Override
    public void checkLengthTest() {

    }

    @Override
    public void addAndCheckLengthTest() {

    }

    @Override
    public void checkSortTest() {

    }

    private QuotePeriod getQuotePeriodDummy() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);
        Date qPDate = cal.getTime();
        int qPDays = 0;
        return new QuotePeriod(qPDate, qPDays);
    }
}
