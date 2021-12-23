package publicadministration;

import java.util.Date;

public class QuotePeriod {  // Represents a quote period as a registered worker
    private Date initDay;
    private int numDays;

    public QuotePeriod(Date date, int nDays) {  // Initializes attributes
        checkNull(date);

        this.initDay = date;
        this.numDays = nDays;
    }

    private void checkNull(Date date) {
        if (date == null) throw new NullPointerException("");
    }

    public Date getInitDay() {
        return initDay;
    }

    public int getNumDays() {
        return numDays;
    }

    public String toString() {
        String header = "Quote Period: ";
        String initDayStr = "Day of inisialization - " + initDay.toString() + " , ";
        String numDaysStr = "Number of days - " + numDays;

        return header + initDayStr + numDaysStr;
    }
}
