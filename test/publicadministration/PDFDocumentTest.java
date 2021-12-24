package publicadministration;

import exceptions.WrongDocPathFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testInterfaces.PDFDocumentTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PDFDocumentTest implements PDFDocumentTestInterface {

    private PDFDocument pdf;

    @BeforeEach
    public void setUp() throws WrongDocPathFormatException {
        pdf = new PDFDocument();
    }

    @Test
    @Override
    public void getCreatDateTest() {
    }

    @Test
    @Override
    public void getPathTest() {

    }

    @Test
    @Override
    public void getFileTest() {

    }

    @Test
    @Override
    public void moveDocTest() {

    }

    @Test
    @Override
    public void openDocTest() {

    }
}
