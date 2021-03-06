package services.interfaces;

import data.Nif;
import controller.exceptions.NotAffiliatedException;
import publicadministration.LaboralLifeDoc;
import publicadministration.MemberAccreditationDoc;

import java.net.ConnectException;

/**
 * External services involved in procedures from population
 */
public interface SSInterface { // External service for Social Security Govern administration
    LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException,
            ConnectException;

    MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException,
            ConnectException;
}
