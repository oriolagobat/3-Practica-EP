package publicadministration;

import data.DocPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.PDFDocumentTestInterface;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PDFDocumentTest implements PDFDocumentTestInterface {

    private PDFDocument pdf;
    private String defaultPath;
    private File file;

    @BeforeEach
    public void setUp() {
        pdf = new PDFDocument();
        defaultPath = "SampleDoc.pdf";
        file = new File(defaultPath);
    }


    @Test
    @Override
    public void getPathTest() {
        assertEquals(defaultPath, pdf.getPath().getDocPath());
    }

    @Test
    @Override
    public void getFileTest() {
        assertEquals(file, pdf.getFile());
    }

    @Test
    @Override
    public void moveDocTest() throws IOException {
        DocPath newPath = new DocPath("out");
        pdf.moveDoc(newPath);
        assertEquals(newPath.getDocPath(), pdf.getPath().getDocPath());
    }

    @Test
    @Override
    public void openDocTest() throws IOException {
        pdf.openDoc(pdf.getPath());
    }
}
