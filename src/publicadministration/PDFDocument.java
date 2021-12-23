package publicadministration;

import data.DocPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

public class PDFDocument {
    private Date creatDate;
    private DocPath path;
    private File file;

    public PDFDocument (Date creatDate, DocPath path, File file) {
        this.creatDate = creatDate;
        this.path = path;
        this.file = file;
    }

    public String toString () {
        return "PDFDocument{" + "Creation Date='" + creatDate.toString() + '\'' + ", " + path.toString() + '\'' + '}';
    }

    // To implement only optionally
    public void moveDoc (DocPath destPath) throws IOException {}
    public void openDoc (DocPath path) throws IOException {}
}
