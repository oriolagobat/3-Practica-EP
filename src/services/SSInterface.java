package services;

import data.Nif;
import controller.exceptions.NotAffiliatedException;
import data.exceptions.WrongDocPathFormatException;
import publicadministration.LaboralLifeDoc;
import publicadministration.MemberAccreditationDoc;

import java.net.ConnectException;

/**
 * External services involved in procedures from population
 */
public interface SSInterface { // External service for Social Security Govern administration
    LaboralLifeDoc getLaboralLife (Nif nif) throws NotAffiliatedException,
            ConnectException, WrongDocPathFormatException;
    MemberAccreditationDoc getMembAccred (Nif nif) throws NotAffiliatedException,
            ConnectException, WrongDocPathFormatException;
}
