package controller.interfaces;

import controller.UnifiedPlatform;
import controller.exceptions.*;

import data.EncryptedData;
import data.EncryptingKey;
import data.exceptions.NotValidCertificateException;
import data.exceptions.WrongDocPathFormatException;
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
import java.nio.charset.StandardCharsets;

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
        outContent.reset();
    }

    @Test
    public void getLaboralLifeDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException,
            NotAffiliatedException, WrongDocPathFormatException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException;

    @Test
    public void getMemberAccredDoc() throws IncorrectValDateException, NifNotRegisteredException,
            AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException,
            WrongDocPathFormatException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException;

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
        String expectedResult = "Mostrant AAPP: SS";
        platform.processSearcher();
        restoreStreams();  // Per a eliminar l'output que genera la crida a processSearcher
        platform.enterKeyWords(emptySearch);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    default void selectExistentCertificationTest() {
        byte report = 1;
        String expectedResult = "Se selecciona: Solicitar el informe de vida laboral";
        platform.selectSS();
        platform.selectCitizens();
        platform.selectReports();
        restoreStreams();  // Per a eliminar l'output que genera la crida a processSearcher
        platform.selectCertificationReport(report);
        assertEquals(expectedResult.strip(), outContent.toString().strip());
    }

    @Test
    default void nullEncryptedDataTest(){
        assertThrows(DecryptationException.class,
                () -> {
                    Decryptor.decryptIDdata(null, new EncryptingKey(BigInteger.ONE));
                });
    }

    @Test
    default void nullEncryptingKeyTest(){
        assertThrows(DecryptationException.class,
                () -> {
                    Decryptor.decryptIDdata(new EncryptedData("sampleNIF".getBytes(Charset.defaultCharset())), null);
                });
    }
}
