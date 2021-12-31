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
    public void moveDoc(DocPath destPath) throws IOException {
        if (!new File(destPath.getDocPath()).exists()) {
            throw new IOException("El path especificat no existeix.");
        } else {
            System.out.println("Movent el document de " + path + " a " + destPath);
            path = destPath;
        }
    }

    public void openDoc(DocPath path) throws IOException {
        if (!new File(path.getDocPath()).exists()) {
            throw new IOException("El document especificat no existeix.");
        } else {
            try {
                File file = new File(path.getDocPath());
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
