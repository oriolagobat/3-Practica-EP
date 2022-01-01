package publicadministration;

import publicadministration.exceptions.WrongQuotePeriodFormatException;

import java.util.Calendar;
import java.util.Date;

public class QuotePeriod {  // Represents a quote period as a registered worker
    private final Date initDay;
    private final int numDays;

    public QuotePeriod(Date date, int nDays) throws WrongQuotePeriodFormatException {  // Initializes attributes
        checkQuotePeriod(date, nDays);

        this.initDay = date;
        this.numDays = nDays;
    }

    private void checkQuotePeriod(Date date, int nDays) throws WrongQuotePeriodFormatException {
        if (date == null) throw new NullPointerException("La data és null a l'hora d'instanciar un QuotePeriod");

        if (wrongFormat(date, nDays)) throw new WrongQuotePeriodFormatException("La dada passada més els dies passats són superiors a la data actual");
    }

    //TODO: Put in memory
    private boolean wrongFormat(Date date, int nDays) {
        // Set calendar to date + ndays time
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, nDays);

        // Get actual date
        Calendar actual = Calendar.getInstance();

        // Return true if date +ndays is greater than actual date
        return cal.compareTo(actual) >= 0;
    }


    public Date getInitDay() {
        return initDay;
    }

    public int getNumDays() {
        return numDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuotePeriod qP = (QuotePeriod) o;
        return (initDay.equals(qP.initDay) && numDays == qP.numDays);
    }

    @Override
    public String toString() {
        String header = "Quote Period: ";
        String initDayStr = "Day of inisialization - " + initDay.toString() + " , ";
        String numDaysStr = "Number of days - " + numDays;

        return header + initDayStr + numDaysStr;
    }
}
