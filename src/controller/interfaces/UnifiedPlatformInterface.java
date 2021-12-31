package controller.interfaces;

import controller.exceptions.*;
import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;
import services.CertificationAuthorityInterface;
import services.SSInterface;

import java.net.ConnectException;
import java.util.Date;

public interface UnifiedPlatformInterface {
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

    // Missing functions implemented in the class due to privacity

    // Other operations implemented in the class due to privacity

    // More operations
    void injectAuthenticationMethod(CertificationAuthorityInterface method);

    void injectSS(SSInterface administration);
}