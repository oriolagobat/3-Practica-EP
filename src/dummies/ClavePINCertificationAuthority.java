package dummies;

import controller.Citizen;
import controller.exceptions.*;
import data.Nif;
import data.PINcode;
import data.Password;
import services.CertificationAuthorityInterface;

import java.net.ConnectException;
import java.util.Date;

public class ClavePINCertificationAuthority implements CertificationAuthorityInterface {

    Citizen citizen = new Citizen();


    public ClavePINCertificationAuthority(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {

        if (!nif.equals(citizen.getNif()))
            throw new NifNotRegisteredException("El NIF no està registrat");

        if (!date.equals(citizen.getValDate()))
            throw new IncorrectValDateException("La data de validació és incorrecta");

        if (citizen.getPhoneNumber() == null)
            throw new AnyMobileRegisteredException("No hi ha cap numero de telefon registrat");

        return true;
    }

    @Override
    public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
        if(!pin.equals(citizen.getPIN()))
            throw new NotValidPINException("PIN incorrecte");

        return true;
    }

    @Override
    public byte ckeckCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        return 0;
    }
}
