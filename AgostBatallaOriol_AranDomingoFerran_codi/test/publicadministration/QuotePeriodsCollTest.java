package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.exceptions.DuplicatedQuotePeriodException;
import publicadministration.exceptions.WrongQuotePeriodFormatException;
import publicadministration.interfaces.QuotePeriodCollTestInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuotePeriodsCollTest implements QuotePeriodCollTestInterface {
    QuotePeriodsColl quotePeriodsColl;
    ArrayList<Date> dateList;

    @BeforeEach
    public void setUp() throws DuplicatedQuotePeriodException, WrongQuotePeriodFormatException {
        quotePeriodsColl = new QuotePeriodsColl();
        dateList = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(1975, Calendar.JANUARY, i);
            Date date = cal.getTime();
            // For the collections test
            dateList.add(date);

            QuotePeriod qP = new QuotePeriod(date, i);

            quotePeriodsColl.addQuotePeriod(qP);
        }
    }

    @Test
    @Override
    public void getQuotePeriodsCollTest() throws WrongQuotePeriodFormatException {
        ArrayList<QuotePeriod> correctQPC = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Date date = dateList.get(i);
            QuotePeriod qP = new QuotePeriod(date, i + 1);

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
    public void addAndCheckLengthTest() throws DuplicatedQuotePeriodException, WrongQuotePeriodFormatException {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JUNE, 6);
        Date date = cal.getTime();
        int correctQuotePeriodsNumb = 6;
        QuotePeriod qP = new QuotePeriod(date, 6);
        quotePeriodsColl.addQuotePeriod(qP);

        assertEquals(correctQuotePeriodsNumb, quotePeriodsColl.getQuotePeriodsCollection().size());
    }

    @Test
    @Override
    public void checkOlderSortTest() throws DuplicatedQuotePeriodException, WrongQuotePeriodFormatException {
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
    public void checkNewerSortTest() throws DuplicatedQuotePeriodException, WrongQuotePeriodFormatException {
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
