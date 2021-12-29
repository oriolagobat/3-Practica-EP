package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.exceptions.DuplicatedQuotePeriodException;
import publicadministration.interfaces.QuotePeriodCollTestInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuotePeriodsCollTest implements QuotePeriodCollTestInterface {
    QuotePeriodsColl quotePeriodsColl;

    @BeforeEach
    public void setUp() throws DuplicatedQuotePeriodException {
        quotePeriodsColl = new QuotePeriodsColl();

        for (int i = 0; i < 5; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(1975, Calendar.JANUARY, i);
            Date date = cal.getTime();

            QuotePeriod qP = new QuotePeriod(date, i);

            quotePeriodsColl.addQuotePeriod(qP);
        }
    }

    @Test
    @Override
    public void getQuotePeriodsCollTest() {
        ArrayList<QuotePeriod> correctQPC = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(1975, Calendar.JANUARY, i);
            Date date = cal.getTime();

            QuotePeriod qP = new QuotePeriod(date, i);

            correctQPC.add(qP);
        }
        correctQPC.sort(Comparator.comparing(QuotePeriod::getInitDay));

        assertEquals(correctQPC, quotePeriodsColl.getQuotePeriodsCollection());
    }

    @Test
    @Override
    public void checkLengthTest() {
        int correctQuotePeriodsNumb = 5;
        assertEquals(correctQuotePeriodsNumb, quotePeriodsColl.getQuotePeriodsCollection().size());
    }

    @Test
    @Override
    public void addAndCheckLengthTest() throws DuplicatedQuotePeriodException {
        int correctQuotePeriodsNumb = 6;
        QuotePeriod qP = new QuotePeriod(new Date(), 0);
        quotePeriodsColl.addQuotePeriod(qP);

        assertEquals(correctQuotePeriodsNumb, quotePeriodsColl.getQuotePeriodsCollection().size());
    }

    @Test
    @Override
    public void checkOlderSortTest() throws DuplicatedQuotePeriodException {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 0);
        Date date = cal.getTime();
        int days = 0;

        QuotePeriod olderQP = new QuotePeriod(date, days);
        quotePeriodsColl.addQuotePeriod(olderQP);

        // Check if first quote period in the array list is the older one just created
        assertEquals(olderQP, quotePeriodsColl.getQuotePeriodsCollection().get(0));
    }

    @Test
    @Override
    public void checkNewerSortTest() throws DuplicatedQuotePeriodException {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.DECEMBER, 31);
        Date date = cal.getTime();
        int days = 0;

        QuotePeriod newerQP = new QuotePeriod(date, days);
        quotePeriodsColl.addQuotePeriod(newerQP);

        // Check if last quote period in the array list is the newer one just created
        int lastIndex = quotePeriodsColl.getQuotePeriodsCollection().size() - 1;
        assertEquals(newerQP, quotePeriodsColl.getQuotePeriodsCollection().get(lastIndex));
    }
}
