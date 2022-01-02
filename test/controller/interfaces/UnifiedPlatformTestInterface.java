package controller.interfaces;

import controller.UnifiedPlatform;
import controller.exceptions.*;

import data.EncryptedData;
import data.EncryptingKey;
import data.exceptions.NotValidCertificateException;
import data.exceptions.WrongNifFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Decryptor;
import services.exceptions.DecryptationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public interface UnifiedPlatformTestInterface {

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    UnifiedPlatform platform = new UnifiedPlatform();

    // Per a poder comprovar el contingut de la sortida estandard
    @BeforeEach
    default void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    default void restoreStreams() {
        outContent.reset();
    }

    @Test
    void getLaboralLifeDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException,
            NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException;

    @Test
    void getMemberAccredDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException,
            NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException;

    @Test
    void selectExistentAuthMethodTest();

    @Test
    default void searchForNonExistentAAPPTest() {
        assertThrows(AnyKeyWordProcedureException.class,
                () -> {
                    String emptySearch = "Ajuntament";
                    platform.processSearcher();
                    platform.enterKeyWords(emptySearch);
                });
    }

    @Test
    default void searchForExistentAAPPTest() throws AnyKeyWordProcedureException {
        String emptySearch = "Solicitar el informe de vida laboral";
        String expectedResult = "[P] S'envia a mostrar l'AAPP: SS";
        platform.processSearcher();
        restoreStreams();  // Per a eliminar l'output que genera la crida a processSearcher
        platform.enterKeyWords(emptySearch);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    default void selectExistentCertificationTest() {
        byte report = 1;
        String expectedResult = "[P] Es selecciona: Solicitar el informe de vida laboral";
        platform.selectSS();
        platform.selectCitizens();
        platform.selectReports();
        restoreStreams();  // Per a eliminar l'output que genera la crida a processSearcher
        platform.selectCertificationReport(report);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    default void nullEncryptedDataTest() {
        assertThrows(DecryptationException.class,
                () -> Decryptor.decryptIDdata(null, new EncryptingKey(BigInteger.ONE)));
    }

    @Test
    default void nullEncryptingKeyTest() {
        assertThrows(DecryptationException.class,
                () -> Decryptor.decryptIDdata(new EncryptedData("sampleNIF".getBytes(Charset.defaultCharset())), null));
    }
}
