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
        if (!nif.equals(citizen.getNif())) throw new NotAffiliatedException("El el ciutadà amb el NIF" + nif + " no està afiliat");
        return new LaboralLifeDoc(citizen.getNif(), new QuotePeriodsColl());
    }

    @Override
    public MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException {
        if (!nif.equals(citizen.getNif())) throw new NotAffiliatedException("El el ciutadà amb el NIF" + nif + " no està afiliat");
        return new MemberAccreditationDoc(citizen.getNif(), citizen.getAccredNumb());

    }
}
