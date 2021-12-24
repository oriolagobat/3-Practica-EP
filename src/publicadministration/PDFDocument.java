package publicadministration;

import data.DocPath;
import exceptions.WrongDocPathFormatException;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PDFDocument {
    private Date creatDate;
    private DocPath path;
    private File file;
    private final static String defaultPath = "";

    public PDFDocument () throws WrongDocPathFormatException {
        this.creatDate = new Date();
        this.path = new DocPath(defaultPath);
        this.file = new File(path.getDocPath());
    }

    public String toString () {
        return "PDFDocument{" + "Creation Date='" + creatDate.toString() + '\'' + ", " + path.toString() + '\'' + '}';
    }

    // To implement only optionally
    public void moveDoc (DocPath destPath) throws IOException {}
    public void openDoc (DocPath path) throws IOException {}
}
