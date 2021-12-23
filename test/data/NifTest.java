package data;

import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testInterfaces.NifTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NifTest implements NifTestInterface {
    Nif nif;

    @BeforeEach
    public void setUp() throws WrongNifFormatException {
        String correctNif = "12345678A";
        nif = new Nif(correctNif);
    }

    @Test
    @Override
    public void getNifTest() {
        String correctNif = "12345678A";
        assertEquals(correctNif, nif.getNif());
    }
}
