package controller.interfaces;

import controller.UnifiedPlatform;
import data.AccredNumb;
import exceptions.AnyKeyWordProcedureException;
import exceptions.WrongAccredNumbFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public interface UnifiedPlatformTestInterface {

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final PrintStream originalOut = System.out;

    UnifiedPlatform platform = new UnifiedPlatform();

    // Per a poder comprovar el contingut de la sortida estandard
    @BeforeEach
    default void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    default void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    default void searchForNonExistentAAPP() {
        assertThrows(AnyKeyWordProcedureException.class,
                () -> {
                    String emptySearch = "Ajuntament";
                    platform.processSearcher();
                    platform.enterKeyWords(emptySearch);
                });
    }

    @Test
    default void searchForExistentAAPP() throws AnyKeyWordProcedureException {
        String emptySearch = "Solicitar el informe de vida laboral";
        String expectedResult = "Mostrant AAPP: SS";
        platform.enterKeyWords(emptySearch);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }
}
