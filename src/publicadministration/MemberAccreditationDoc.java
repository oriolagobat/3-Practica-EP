package publicadministration;

import data.AccredNumb;
import data.Nif;
import data.exceptions.WrongDocPathFormatException;

public class MemberAccreditationDoc extends PDFDocument{
    private final Nif nif;
    private final AccredNumb numbAffil;

    public MemberAccreditationDoc (Nif nif, AccredNumb numbAffil) throws WrongDocPathFormatException {
        this.nif = nif;
        this.numbAffil = numbAffil;
    }

    public Nif getNif() {
        return nif;
    }

    public AccredNumb getNumbAffil() {
        return numbAffil;
    }
}
