package testInterfaces;

import data.DocPath;
import data.Nif;
import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.Test;
import publicadministration.PDFDocument;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface PDFDocumentTestInterface {

    @Test
    void getCreatDateTest();

    @Test
    void getPathTest();

    @Test
    void getFileTest();

    @Test
    void moveDocTest();

    @Test
    void openDocTest();

    @Test
    default void moveToNonExistentPathTest() {
        assertThrows(IOException.class,
                () -> {
                    DocPath nonExistentPath = new DocPath("aaa");
                    PDFDocument pdf = new PDFDocument();
                    pdf.moveDoc(nonExistentPath);
                });
    }

    @Test
    default void openNonExistentPathTest() {
        assertThrows(IOException.class,
                () -> {
                    DocPath nonExistentPath = new DocPath(".///");
                    PDFDocument pdf = new PDFDocument();
                    pdf.openDoc(nonExistentPath);
                });
    }
}
