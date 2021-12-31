package publicadministration;

import data.Nif;

public class LaboralLifeDoc extends PDFDocument {
    private final Nif nif;
    private final QuotePeriodsColl quotePds;

    public LaboralLifeDoc(Nif nif, QuotePeriodsColl quotePds) {
        checkLaboralLifeDoc(nif, quotePds);

        this.nif = nif;
        this.quotePds = quotePds;
    }

    private void checkLaboralLifeDoc(Nif nif, QuotePeriodsColl quotePds) {
        if (nif == null || quotePds == null) {
            throw new NullPointerException("El nif o quotePds de LaboralLifeDoc són null");
        }
    }

    public Nif getNif() {
        return nif;
    }

    public QuotePeriodsColl getQuotePds() {
        return quotePds;
    }
}
