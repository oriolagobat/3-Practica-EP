package publicadministration;

import data.DocPath;
import data.Nif;

import java.io.File;
import java.util.Date;

public class LaboralLifeDoc extends PDFDocument{
    private Nif nif;
    private QuotePeriodsColl quotePds;

    public LaboralLifeDoc (Nif nif, QuotePeriodsColl quotePds) {
        this.nif = nif;
        this.quotePds = quotePds;
    }

}
