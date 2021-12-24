package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.QuotePeriodTestInterface;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class QuotePeriodTest implements QuotePeriodTestInterface {
    QuotePeriod quotePeriod;
    Date date;  // Common date

    @BeforeEach
    public void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);
        date = cal.getTime();
        int qPDays = 0;
        quotePeriod = new QuotePeriod(date, qPDays);
    }

    @Test
    @Override
    public void getInitDayTest() {
        assertEquals(date, quotePeriod.getInitDay());
    }

    @Test
    @Override
    public void getNumDaysTest() {
        int qPDays = 0;
        assertEquals(qPDays, quotePeriod.getNumDays());
    }
}
