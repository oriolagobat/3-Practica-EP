package publicadministration;

import publicadministration.QuotePeriod;

import java.util.ArrayList;
import java.util.Comparator;

public class QuotePeriodsColl {  // Represents the total quota periods known as a registered worker
    ArrayList<QuotePeriod> quotePeriodsCollection;

    public QuotePeriodsColl() {
        this.quotePeriodsCollection = new ArrayList<>();
    }

    public ArrayList<QuotePeriod> getQuotePeriodsCollection() {
        return quotePeriodsCollection;
    }

    public void addQuotePeriod (QuotePeriod qPd) {
        quotePeriodsCollection.add(qPd);
        quotePeriodsCollection.sort(Comparator.comparing(QuotePeriod::getInitDay));  // Sorts array list by date
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Quotes periods in the collection: ");
        for (QuotePeriod qPd : quotePeriodsCollection) {
            result.append(qPd.toString());
        }

        return result.toString();
    }
}
