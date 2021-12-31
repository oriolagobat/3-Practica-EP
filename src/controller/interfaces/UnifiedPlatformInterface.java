package controller.interfaces;

import controller.exceptions.*;
import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;

import java.net.ConnectException;
import java.util.Date;

public interface UnifiedPlatformInterface {

    // Setters of needed data structures
    void setAAPP();

    void setServices();

    void updateServiceMapKey();

    void setDigitalCertificates();

    void setEncryptingKeys();

    // School tasks
    void processSearcher();

    void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException;

    void selectSS();

    void selectCitizens();

    void selectReports();

    void selectCertificationReport(byte opc);

    void selectAuthMethod(byte opc);

    void enterNIFandPINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException;

    void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, ConnectException;

    void enterCred(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException;

    void printDocument() throws BadPathException, PrintingException;

    void downloadDocument();

    void selectPath(DocPath path) throws BadPathException;

    // Other operations
    String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException;

    void openDocument(DocPath path) throws BadPathException;

    void printDocument(DocPath path) throws BadPathException, PrintingException;

    void downloadDocument(DocPath path) throws BadPathException;

    // More operations

}
