package dummies;

import controller.Citizen;
import controller.exceptions.NotAffiliatedException;
import data.Nif;
import publicadministration.LaboralLifeDoc;
import publicadministration.MemberAccreditationDoc;
import publicadministration.QuotePeriodsColl;
import services.interfaces.SSInterface;

import java.net.ConnectException;

public class SS implements SSInterface {

    Citizen citizen;

    public SS(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException, ConnectException {
        return new LaboralLifeDoc(citizen.getNif(), new QuotePeriodsColl());
    }

    @Override
    public MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException {
        return new MemberAccreditationDoc(citizen.getNif(), citizen.getAccredNumb());

    }
}
