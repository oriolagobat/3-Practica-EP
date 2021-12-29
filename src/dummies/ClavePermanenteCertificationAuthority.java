package dummies;

import controller.Citizen;
import data.Nif;
import data.PINcode;
import data.Password;
import exceptions.*;
import services.CertificationAuthorityInterface;

import java.net.ConnectException;
import java.util.Date;

public class ClavePermanenteCertificationAuthority implements CertificationAuthorityInterface {

    Citizen citizen = new Citizen();


    public ClavePermanenteCertificationAuthority(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        return false;
    }

    @Override
    public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
        if(!pin.equals(citizen.getPIN()))
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
}
