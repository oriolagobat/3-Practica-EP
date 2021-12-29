package publicadministration;

import publicadministration.exceptions.DuplicatedQuotePeriodException;

import java.util.ArrayList;
import java.util.Comparator;

public class QuotePeriodsColl {  // Represents the total quota periods known as a registered worker
    private final ArrayList<QuotePeriod> quotePeriodsCollection;

    public QuotePeriodsColl() {
        this.quotePeriodsCollection = new ArrayList<>();
    }

    public ArrayList<QuotePeriod> getQuotePeriodsCollection() {
        return quotePeriodsCollection;
    }

    public void addQuotePeriod(QuotePeriod qPd) throws DuplicatedQuotePeriodException {
        if (qPd == null) {
            throw new NullPointerException("El periòde de cotització que s'intenta afegir és null");
        }
        for (QuotePeriod quotePeriod : quotePeriodsCollection) {
            if (qPd.equals(quotePeriod)) {
                throw new DuplicatedQuotePeriodException("El període de cotització que s'intenta afegir ja està afegit");
            }
        }

        quotePeriodsCollection.add(qPd);
        quotePeriodsCollection.sort(Comparator.comparing(QuotePeriod::getInitDay));  // Sorts array list by date
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Quotes periods in the collection: ");
        for (QuotePeriod qPd : quotePeriodsCollection) {
            result.append(qPd.toString());
        }

        return result.toString();
    }
}
