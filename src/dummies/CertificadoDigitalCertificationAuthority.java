package dummies;

import controller.exceptions.*;
import data.*;
import data.exceptions.NotValidCertificateException;
import services.CertificationAuthorityInterface;

import java.net.ConnectException;
import java.util.Date;

public class CertificadoDigitalCertificationAuthority implements CertificationAuthorityInterface {
    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        return false;
    }

    @Override
    public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
        return false;
    }

    @Override
    public byte ckeckCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        return 0;
    }

    @Override
    public EncryptedData sendCertfAuth(EncryptingKey pubKey) throws NotValidCertificateException, ConnectException {
        return null;
    }
}
