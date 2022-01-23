package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.interfaces.DocPathTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocPathTest implements DocPathTestInterface {
    DocPath docPath;

    @BeforeEach
    public void setUp() {
        String correctDocPath = "/home/oriolagobat/git/result.out";
        docPath = new DocPath(correctDocPath);
    }

    @Test
    @Override
    public void getDocPathTest() {
        String correctDocPath = "/home/oriolagobat/git/result.out";
        assertEquals(correctDocPath, docPath.getDocPath());
    }
}
