package data;

import exceptions.WrongDocPathFormatException;
import org.junit.jupiter.api.BeforeEach;
import testInterfaces.DocPathTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocPathTest implements DocPathTestInterface {
    DocPath docPath;

    @BeforeEach
    public void setUp() throws WrongDocPathFormatException {
        String correctDocPath = "/home/oriolagobat/git/result.out";
        docPath = new DocPath(correctDocPath);
    }

    @Override
    public void getDocPathTest() {
        String correctDocPath = "/home/oriolagobat/git/result.out";
        assertEquals(correctDocPath, docPath.getDocPath());
    }
}
