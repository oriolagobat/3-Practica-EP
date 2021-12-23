package publicadministration;

import data.AccredNumb;
import data.Nif;

public class MemberAccreditationDoc extends PDFDocument{
    private Nif nif;
    private AccredNumb numbAffil;

    public MemberAccreditationDoc (Nif nif, AccredNumb numbAffil) {
        this.nif = nif;
        this.numbAffil = numbAffil;
    }
}
