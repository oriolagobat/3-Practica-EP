package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformTestInterface;
import data.AccredNumb;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.*;
import dummies.CertificadoDigitalCertificationAuthority;
import dummies.SS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.exceptions.DecryptationException;

import java.io.IOException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        platform.injectAuthenticationMethod(new CertificadoDigitalCertificationAuthority(citizen));
    }

    @Test
    @Override
    public void selectExistentAuthMethodTest() {
        byte method = 3;
        String expectedResult = "[P] Es selecciona el mètode d'autenticació Certificado digital";
        platform.selectAuthMethod(method);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    public void selectCertificateTest() {
        String correctCertification = "Certificados cualificados de Sede electrónica de la Administración Pública";
        platform.selectCertificate((byte) 2);
        assertEquals(correctCertification, platform.selectedCertification);
    }

    @Test
    public void enterPasswTest() throws WrongPasswordFormatException, NotValidPasswordException, NotValidCertificateException, IOException, NotAffiliatedException, DecryptationException, WrongNifFormatException, BadPathException {
        Password password = new Password("contrasenya123$");
        platform.enterPassw(password);
        assertEquals(password, platform.citz.getPassword());
    }


    @Test
    public void invalidPasswTest() {
        assertThrows(NotValidPasswordException.class, () -> platform.enterPassw(null));
    }


    @Test
    @Override
    public void getLaboralLifeDoc() throws IOException, NotAffiliatedException,
            NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException {
        byte report = 1;
        String expectedResult = "[P] S'envïen per a la seva desencriptació les dades" +
                "[P] Mostrant informe de la vida laboral...";

        platform.injectSS(new SS(citizen));
        platform.selectCertificationReport(report);

        outContent.reset();
        platform.enterPassw(citizen.getPassword());

        assertEquals(expectedResult.replaceAll("[^a-zA-Z0-9]", ""), outContent.toString().replaceAll("[^a-zA-Z0-9]", ""));
    }

    @Test
    @Override
    public void getMemberAccredDoc() throws IOException, NotAffiliatedException,
            NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException {
        byte report = 2;
        String expectedResult = "[P] S'envïen per a la seva desencriptació les dades" +
                "[P] Mostrant nombre d'acreditació de la SS...";

        platform.injectSS(new SS(citizen));
        platform.selectCertificationReport(report);

        outContent.reset();
        platform.enterPassw(citizen.getPassword());

        assertEquals(expectedResult.replaceAll("[^a-zA-Z0-9]", ""), outContent.toString().replaceAll("[^a-zA-Z0-9]", ""));
    }
}
