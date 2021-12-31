package publicadministration;

import data.AccredNumb;
import data.Nif;

public class MemberAccreditationDoc extends PDFDocument {
    private final Nif nif;
    private final AccredNumb numbAffil;

    public MemberAccreditationDoc(Nif nif, AccredNumb numbAffil) {
        checkMemberAccreditationDoc(nif, numbAffil);

        this.nif = nif;
        this.numbAffil = numbAffil;
    }

    private void checkMemberAccreditationDoc(Nif nif, AccredNumb numbAffil) {
        if (nif == null || numbAffil == null) {
            throw new NullPointerException("El nif o el nombre d'afiliació és null en MemberAccreditationDoc");
        }
    }

    public Nif getNif() {
        return nif;
    }

    public AccredNumb getNumbAffil() {
        return numbAffil;
    }
}
