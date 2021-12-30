package controller;

import controller.interfaces.UnifiedPlatformTestInterface;
import org.junit.jupiter.api.Test;

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
}
