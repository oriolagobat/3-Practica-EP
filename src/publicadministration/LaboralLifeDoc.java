package publicadministration;

import data.DocPath;
import data.Nif;
import exceptions.WrongDocPathFormatException;

import java.io.File;
import java.util.Date;

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
