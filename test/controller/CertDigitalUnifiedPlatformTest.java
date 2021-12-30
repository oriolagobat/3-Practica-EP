package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformTestInterface;
import data.AccredNumb;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.*;
import dummies.ClavePermanenteCertificationAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertDigitalUnifiedPlatformTest implements UnifiedPlatformTestInterface {

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

    @Test
    @Override
    public void selectExistentAuthMethodTest() {
        byte method = 3;
        String expectedResult = "Se selecciona el método de autenticación Certificado digital";
        platform.selectAuthMethod(method);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    @Override
    public void getLaboralLifeDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException,
            NotAffiliatedException, WrongDocPathFormatException {
    }

    @Test
    @Override
    public void getMemberAccredDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException,
            NotAffiliatedException, WrongDocPathFormatException {

    }

    @Test
    public void selectCertificateTest() {
        String correctCertification = "Certificados cualificados de Sede electrónica de la Administración Pública";
        platform.selectCertificate((byte) 2);
        assertEquals(correctCertification, platform.selectedCertification);
    }

    @Test
    public void enterPasswTest() throws WrongPasswordFormatException, NotValidPasswordException {
        Password password = new Password("contrasenya123$");
        platform.enterPassw(password);
        assertEquals(password, platform.citz.getPassword());
    }
}
