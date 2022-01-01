package publicadministration;

import data.DocPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PDFDocument {
    private final Date creatDate;
    private final File file;
    private DocPath path;
    private final static String defaultPath = "SampleDoc.pdf";

    public PDFDocument() {
        this.creatDate = new Date();
        this.path = new DocPath(defaultPath);
        this.file = new File(path.getDocPath());
    }

    public DocPath getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public String toString() {
        return "PDFDocument{" + "Creation Date='" + creatDate.toString() + '\'' + ", " + path.toString() + '\'' + '}';
    }

    // To implement only optionally
    // TODO: Yet to be implemented
    public void moveDoc(DocPath destPath) throws IOException {
        if (!new File(destPath.getDocPath()).exists()) {
            throw new IOException("El path especificat no existeix.");
        } else {
            System.out.println("Movent el document de " + path + " a " + destPath);
            path = destPath;
        }
    }

    //TODO: No try catch, open already throws IOException
    public void openDoc(DocPath path) throws IOException {
        // IOException is only thrown if there's no application to open the file,
        // We also throw it if the path doesn't exist
        if (!new File(path.getDocPath()).exists()) {
            throw new IOException("El document especificat no existeix.");
        } else {
            File file = new File(path.getDocPath());
            Desktop.getDesktop().open(file);
        }
    }
}
