package controller;

import controller.interfaces.UnifiedPlatformTestInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClavePINUnifiedPlatformTest implements UnifiedPlatformTestInterface {

    @Test
    @Override
    public void selectExistentAuthMethodTest() {
        byte method = 0;
        String expectedResult = "Se selecciona el método de autenticación Cl@ve PIN";
        platform.selectAuthMethod(method);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

}
