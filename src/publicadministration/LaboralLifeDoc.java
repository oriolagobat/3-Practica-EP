package publicadministration;

import data.Nif;

public class LaboralLifeDoc extends PDFDocument {
    private final Nif nif;
    private final QuotePeriodsColl quotePds;

    public LaboralLifeDoc(Nif nif, QuotePeriodsColl quotePds) {
        this.nif = nif;
        this.quotePds = quotePds;
    }

    public Nif getNif() {
        return nif;
    }

    public QuotePeriodsColl getQuotePds() {
        return quotePds;
    }
}
