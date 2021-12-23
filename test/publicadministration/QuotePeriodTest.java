package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import publicadministration.QuotePeriod;
import testInterfaces.QuotePeriodInterface;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuotePeriodTest implements QuotePeriodInterface {
    QuotePeriod quotePeriod;

    @BeforeEach
    public void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);
        Date qPDate = cal.getTime();
        int qPDays = 0;
        quotePeriod = new QuotePeriod(qPDate, qPDays);
    }

    @Override
    public void getInitDayTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);
        Date qPDate = cal.getTime();
        assertEquals(qPDate, quotePeriod.getInitDay());
    }

    @Override
    public void getNumDaysTest() {
        int qPDays = 0;
        assertEquals(qPDays, quotePeriod.getNumDays());
    }
}
