package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformTestInterface;
import data.exceptions.WrongDocPathFormatException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertDigitalUnifiedPlatformTest implements UnifiedPlatformTestInterface {

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
}
