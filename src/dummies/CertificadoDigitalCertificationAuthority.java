package dummies;

import data.Nif;
import data.PINcode;
import data.Password;
import exceptions.*;
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
}
