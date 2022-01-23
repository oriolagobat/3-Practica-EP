package dummies;

import controller.Citizen;
import controller.exceptions.*;
import data.*;
import data.exceptions.NotValidCertificateException;
import services.interfaces.CertificationAuthorityInterface;

import java.net.ConnectException;
import java.util.Date;

public class ClavePermanenteCertificationAuthority implements CertificationAuthorityInterface {

    Citizen citizen;

    public ClavePermanenteCertificationAuthority(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        return false;
    }

    @Override
    public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
        if (!pin.equals(citizen.getPIN()))
            throw new NotValidPINException("PIN incorrecte");

        return true;
    }

    @Override
    public byte ckeckCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {

        if (!nif.equals(citizen.getNif()))
            return 0;

        if (!passw.equals(citizen.getPassword()))
            throw new NotValidCredException("La contrasenya és incorrecta");

        if (citizen.getPhoneNumber() == null)
            throw new AnyMobileRegisteredException("No hi ha cap numero de telefon registrat");

        if (citizen.hasReinforcedPINActivated())
            return 2;

        return 1;
    }

    @Override
    public EncryptedData sendCertfAuth(EncryptingKey pubKey) throws NotValidCertificateException, ConnectException {
        return null;
    }
}
