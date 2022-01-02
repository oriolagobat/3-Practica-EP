package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformTestInterface;
import data.AccredNumb;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.*;
import dummies.ClavePermanenteCertificationAuthority;
import dummies.SS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClavePermanenteUnifiedPlatformTest implements UnifiedPlatformTestInterface {

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

        platform.injectAuthenticationMethod(new ClavePermanenteCertificationAuthority(citizen));
    }
    
    @AfterEach
    public void unSetAdministration(){
        platform.injectSS(null);
    }

    @Test
    @Override
    public void selectExistentAuthMethodTest() {
        byte method = 2;
        String expectedResult = "[P] Es selecciona el mètode d'autenticació Cl@ve Permanente";
        platform.selectAuthMethod(method);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void correctCredReinforced() throws NifNotRegisteredException, AnyMobileRegisteredException,
            ConnectException, NotValidCredException {
        String expectedResult = "[P] Les dades de l'usuari són correctes, no s'ha escollit el mètode reforçat";
        citizen.setReinforcedPINActivated(false);
        platform.enterCred(citizen.getNif(), citizen.getPassword());
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void correctCredNoReinforced() throws NifNotRegisteredException, AnyMobileRegisteredException,
            ConnectException, NotValidCredException {
        String expectedResult = "[P] Les dades de l'usuari són correctes, s'ha escollit el mètode reforçat";
        citizen.setReinforcedPINActivated(true);
        platform.enterCred(citizen.getNif(), citizen.getPassword());
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void notRegisteredNif() {
        assertThrows(NifNotRegisteredException.class,
                () -> platform.enterCred(new Nif("12345678A"), citizen.getPassword()));
    }

    @Test
    public void anyPhoneNumber() {
        assertThrows(AnyMobileRegisteredException.class,
                () -> {
                    citizen.setPhoneNumber(null);
                    platform.enterCred(citizen.getNif(), citizen.getPassword());
                });
    }

    @Test
    public void notValidCred() {
        assertThrows(NotValidCredException.class,
                () -> platform.enterCred(citizen.getNif(), new Password("contrasenya1234$$")));
    }

    @Test
    public void correctEnterPINTest() throws NotValidPINException, NotAffiliatedException,
            IOException, BadPathException {
        String expectedResult = "[P] El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent";
        platform.injectSS(null);
        platform.enterPIN(citizen.getPIN());
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void incorrectPINTest() {
        assertThrows(NotValidPINException.class,
                () -> platform.enterPIN(new PINcode("978")));
    }

    @Test
    @Override
    public void getLaboralLifeDoc() throws NifNotRegisteredException, AnyMobileRegisteredException, IOException,
            NotValidPINException, NotAffiliatedException, NotValidCredException, BadPathException {

        byte report = 1;
        String expectedResult = "[P] El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent\n"
                + "[P] Mostrant informe de la vida laboral...";

        platform.enterCred(citizen.getNif(), citizen.getPassword());
        platform.injectSS(new SS(citizen));
        platform.selectCertificationReport(report);

        outContent.reset();
        platform.enterPIN(citizen.getPIN());

        assertEquals(expectedResult.replaceAll("[^a-zA-Z0-9]", ""), outContent.toString().replaceAll("[^a-zA-Z0-9]", ""));

    }

    @Test
    @Override
    public void getMemberAccredDoc() throws NifNotRegisteredException, AnyMobileRegisteredException, IOException,
            NotValidPINException, NotAffiliatedException, NotValidCredException, BadPathException {
        byte report = 2;
        String expectedResult = "[P] El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent\n"
                + "[P] Mostrant nombre d'acreditació de la SS...";

        platform.enterCred(citizen.getNif(), citizen.getPassword());
        platform.injectSS(new SS(citizen));
        platform.selectCertificationReport(report);

        outContent.reset();
        platform.enterPIN(citizen.getPIN());

        assertEquals(expectedResult.replaceAll("[^a-zA-Z0-9]", ""), outContent.toString().replaceAll("[^a-zA-Z0-9]", ""));

    }
}
