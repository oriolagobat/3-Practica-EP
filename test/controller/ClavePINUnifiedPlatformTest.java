package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformTestInterface;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.*;
import dummies.ClavePINCertificationAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClavePINUnifiedPlatformTest implements UnifiedPlatformTestInterface {

    Citizen citizen;

    @BeforeEach
    public void setUp() throws WrongNifFormatException, WrongPasswordFormatException, WrongPINCodeFormatException {
        citizen = new Citizen();
        citizen.setPIN(new PINcode("123"));
        citizen.setPhoneNumber("777888999");
        citizen.setNif(new Nif("11223344F"));
        citizen.setPassword(new Password("$contrasenya123"));

        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 16);

        citizen.setValDate(cal.getTime());

        platform.injectAuthenticationMethod(new ClavePINCertificationAuthority(citizen));
    }

    @Test
    @Override
    public void selectExistentAuthMethodTest() {
        byte method = 0;
        String expectedResult = "Se selecciona el método de autenticación Cl@ve PIN";
        platform.selectAuthMethod(method);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void correctPINObtTest() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException {
        String expectedResult = "Se envia el PIN al usuario con DNI: 11223344F";
        platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void notRegisteredNif(){
        assertThrows(NifNotRegisteredException.class,
                () -> {
                    platform.enterNIFandPINobt(new Nif("00112233Q"), citizen.getValDate());
                });
    }

    @Test
    public void incorrectValDate(){
        assertThrows(IncorrectValDateException.class,
                () -> {
                    platform.enterNIFandPINobt(citizen.getNif(), new Date());
                });
    }

    @Test
    public void anyPhoneNumber(){
        assertThrows(AnyMobileRegisteredException.class,
                () -> {
                    citizen.setPhoneNumber(null);
                    platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
                });
    }

    @Test
    public void correctEnterPINTest() throws NotValidPINException, NotAffiliatedException, ConnectException {
        String expectedResult = "El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent";
        platform.enterPIN(citizen.getPIN());
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void incorrectPINTest(){
        assertThrows(NotValidPINException.class,
                () -> {
                    platform.enterPIN(new PINcode("978"));
                });
    }
}
