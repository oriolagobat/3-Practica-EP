package publicadministration;

import data.DocPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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

    public void moveDoc(DocPath destPath) throws IOException {
        // IOException is only thrown if there's an I/O Error,
        // We also throw it if the path doesn't exist
        if (!new File(destPath.getDocPath()).exists()) throw new IOException("El path especificat no existeix.");
        Path sourcePath = Paths.get(path.getDocPath());
        Path targetPath = Paths.get(defaultPath);
        Files.move(sourcePath, targetPath, REPLACE_EXISTING);
        System.out.println("Movent el document de " + path + " a " + destPath);
        path = destPath;
    }

    public void openDoc(DocPath path) throws IOException {
        // IOException is only thrown if there's no application to open the file,
        // We also throw it if the path doesn't exist
        if (!new File(path.getDocPath()).exists()) throw new IOException("El document especificat no existeix.");
        File file = new File(path.getDocPath());
        Desktop.getDesktop().open(file);
    }
}
