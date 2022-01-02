package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformTestInterface;
import data.AccredNumb;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.*;
import dummies.ClavePINCertificationAuthority;
import dummies.SS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClavePINUnifiedPlatformTest implements UnifiedPlatformTestInterface {

    Citizen citizen;

    @BeforeEach
    public void setUp() throws WrongNifFormatException, WrongPasswordFormatException,
            WrongPINCodeFormatException, WrongAccredNumbFormatException {
        citizen = new Citizen();
        citizen.setPIN(new PINcode("123"));
        citizen.setPhoneNumber("777888999");
        citizen.setNif(new Nif("11223344F"));
        citizen.setPassword(new Password("$contrasenya123"));
        citizen.setAccredNumb(new AccredNumb("000111222"));

        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);

        citizen.setValDate(cal.getTime());

        platform.injectAuthenticationMethod(new ClavePINCertificationAuthority(citizen));
    }

    @AfterEach
    public void unSetAdministration(){
        platform.injectSS(null);
    }

    @Test
    @Override
    public void selectExistentAuthMethodTest() {
        byte method = 1;
        String expectedResult = "[P] Es selecciona el mètode d'autenticació Cl@ve PIN";
        platform.selectAuthMethod(method);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void correctPINObtTest() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, ConnectException {
        String expectedResult = "[P] S'envia el PIN a l'usuari amb DNI: 11223344F";
        platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void notRegisteredNif() {
        assertThrows(NifNotRegisteredException.class,
                () -> platform.enterNIFandPINobt(new Nif("00112233Q"), citizen.getValDate()));
    }

    @Test
    public void incorrectValDate() {
        assertThrows(IncorrectValDateException.class,
                () -> platform.enterNIFandPINobt(citizen.getNif(), new Date()));
    }

    @Test
    public void anyPhoneNumber() {
        assertThrows(AnyMobileRegisteredException.class,
                () -> {
                    citizen.setPhoneNumber(null);
                    platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
                });
    }

    @Test
    public void correctEnterPINTest() throws NotValidPINException, NotAffiliatedException,
            IOException {
        String expectedResult = "[P] El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent\n";
        platform.enterPIN(citizen.getPIN());
        assertEquals(expectedResult.strip().replaceAll("[^a-zA-Z0-9]", ""),
                outContent.toString().strip().replaceAll("[^a-zA-Z0-9]", ""));
    }

    @Test
    public void incorrectPINTest() {
        assertThrows(NotValidPINException.class,
                () -> platform.enterPIN(new PINcode("978")));
    }

    @Test
    @Override
    public void getLaboralLifeDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException,
            NotAffiliatedException {

        byte report = 1;
        String expectedResult = "[P] El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent\n"
                + "[P] Mostrant informe de la vida laboral...";

        platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
        platform.injectSS(new SS(citizen));
        platform.selectCertificationReport(report);

        outContent.reset();
        platform.enterPIN(citizen.getPIN());

        assertEquals(expectedResult.replaceAll("[^a-zA-Z0-9]", ""), outContent.toString().replaceAll("[^a-zA-Z0-9]", ""));
    }

    @Test
    @Override
    public void getMemberAccredDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException {

        byte report = 2;
        String expectedResult = "[P] El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent\n"
                + "[P] Mostrant nombre d'acreditació de la SS...";

        platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
        platform.injectSS(new SS(citizen));
        platform.selectCertificationReport(report);

        outContent.reset();
        platform.enterPIN(citizen.getPIN());

        assertEquals(expectedResult.replaceAll("[^a-zA-Z0-9]", ""), outContent.toString().replaceAll("[^a-zA-Z0-9]", ""));
    }
}
