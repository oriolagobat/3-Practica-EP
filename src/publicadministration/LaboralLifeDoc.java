package publicadministration;

import data.Nif;
import data.exceptions.WrongDocPathFormatException;

public class LaboralLifeDoc extends PDFDocument{
    private final Nif nif;
    private final QuotePeriodsColl quotePds;

    public LaboralLifeDoc (Nif nif, QuotePeriodsColl quotePds) throws WrongDocPathFormatException {
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
