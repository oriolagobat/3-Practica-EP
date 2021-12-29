package services;

import controller.exceptions.*;
import data.Nif;
import data.PINcode;
import data.Password;

import java.net.ConnectException;
import java.util.Date;

public interface CertificationAuthorityInterface {
    boolean sendPIN (Nif nif, Date date) throws NifNotRegisteredException,
            IncorrectValDateException, AnyMobileRegisteredException, ConnectException;
    boolean checkPIN (Nif nif, PINcode pin) throws NotValidPINException,
            ConnectException;
    byte ckeckCredent (Nif nif, Password passw) throws NifNotRegisteredException,
            NotValidCredException, AnyMobileRegisteredException, ConnectException;
}