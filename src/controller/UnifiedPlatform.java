package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformInterface;
import data.*;

import data.exceptions.NotValidCertificateException;
import data.exceptions.WrongNifFormatException;
import publicadministration.PDFDocument;
import services.CertificationAuthorityInterface;
import services.Decryptor;
import services.SSInterface;
import services.exceptions.DecryptationException;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UnifiedPlatform implements UnifiedPlatformInterface {
    Citizen citz;
    SSInterface administration;
    CertificationAuthorityInterface authMethod;

    HashMap<String, String> aapp;
    HashMap<String, ArrayList<String>> services;
    ArrayList<String> possibleAuthMethods;


    // Optional - Digital Certificate
    String selectedCertification = null;
    ArrayList<String> possibleDigitalCertificates;

    EncryptingKey publicKey;
    EncryptingKey privateKey;

    public UnifiedPlatform() {
        this.citz = new Citizen();
        this.aapp = new HashMap<>();
        setAAPP();

        this.services = new HashMap<>();
        setServices();

        this.possibleAuthMethods = new ArrayList<>();
        setAuthMethods();

        this.possibleDigitalCertificates = new ArrayList<>();
        setDigitalCertificates();

        setEncryptingKeys();
    }

    private void setAAPP() {
        // Seguridad Social (SS)
        aapp.put("Solicitar el informe de vida laboral", "SS");
        aapp.put("Obtener acreditación del número de afiliación a la Seguridad Social", "SS");

        // Agència Estatal de la Administración Tributaria (AEAT)
        aapp.put("Obtener datos fiscales", "AEAT");
        aapp.put("Tramitar el borrador de la declaración de la renta", "AEAT");

        // Ministerio de Justícia (MJ)
        aapp.put("Solicitar el certificado de nacimiento", "MJ");

        // Dirección General de Tráfico (DGT)
        aapp.put("Consultar los puntos asociados al carnet de conducir", "DGT");

        // WE COULD ADD MORE AAPP'S //
    }

    private void setServices() {
        // Seguridad Social (SS)
        updateServiceMapKey("SS", "Solicitar el informe de vida laboral");
        updateServiceMapKey("SS", "Obtener acreditación del número de afiliación a la Seguridad Social");

        // Agència Estatal de la Administración Tributaria (AEAT)
        updateServiceMapKey("AEAT", "Obtener datos fiscales");
        updateServiceMapKey("AEAT", "Tramitar el borrador de la declaración de la renta");

        // Ministerio de Justícia (MJ)
        updateServiceMapKey("MJ", "Solicitar el certificado de nacimiento");

        // Dirección General de Tráfico (DGT)
        updateServiceMapKey("DGT", "Consultar los puntos asociados al carnet de conducir");

        // WE COULD ADD MORE SERVICES //
    }

    private void updateServiceMapKey(String key, String value) {
        ArrayList<String> values;
        if (services.get(key) == null) {  // First value
            values = new ArrayList<>();
        } else {  // There are already values
            values = services.get(key);
            services.remove(key);
        }
        // Add the value
        values.add(value);
        // Override it with the new list
        services.put(key, values);
    }

    private void setAuthMethods() {
        possibleAuthMethods.add("Cl@ve PIN");
        possibleAuthMethods.add("Cl@ve Permanente");
        possibleAuthMethods.add("Certificado digital");

        // WE COULD ADD MORE METHODS //
    }

    private void setDigitalCertificates() {
        // We've set the possible digital certificate to those qualified for the AAPP's,
        // as found on https://redtrust.com/tipos-de-certificados-digitales/
        possibleDigitalCertificates.add("Certificados electrónicos cualificados de Empleado Público");
        possibleDigitalCertificates.add("Certificados cualificados de Sede electrónica de la Administración Pública");
        possibleDigitalCertificates.add("Certificados cualificados de Sello electrónico de la Administración Pública");
    }

    // They will have the same value because we're just emulating the process of encryption and decryption
    private void setEncryptingKeys() {
        this.privateKey = new EncryptingKey(new BigInteger("1234"));
        this.publicKey = new EncryptingKey(new BigInteger("1234"));
    }

    // School tasks

    public void processSearcher() {
        System.out.println("Es procedeix a usar el buscardor de tràmits");
        System.out.println("Es desplega el buscador");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String result = searchKeyWords(keyWord);
        switch (result) {
            case "SS":
                System.out.println("Mostrant AAPP: " + result);
                break;
            // In other cases
            case "AEAT":
                // selectAEAT();
                break;
            case "MJ":
                // selectMJ();
                break;
            case "DGT":
                // selectDGT();
                break;
            // OTHER AAPP'S WOULD ALSO BE ADDED HERE //
        }
    }

    public void selectSS() {
        System.out.println("Se hace click en la sección SS del mosaico inicial");
    }

    public void selectCitizens() {
        System.out.println("Se hace click en el enlace Ciudadanos de la sección de la SS");
    }

    public void selectReports() {
        System.out.println("Se hace click en el enlace Informes y certificados del apartado Ciudadanos de la SS");
    }

    public void selectCertificationReport(byte opc) {
        // 0 is Laboral Life Doc
        // 1 SS affiliation number
        // We do this to acces through the dictionary
        // ASSUMING THAT SERCIVES IN THE DICTIONARY WILL BE IN THE SAME ORDER AS IN THE WEB PAGE
        // Since certifications are only available through SS, we asume citizen is using that AAPP
        ArrayList<String> ssServices = services.get("SS");
        this.selectedCertification = ssServices.get(opc - 1);
        System.out.println("Se selecciona: " + selectedCertification);
    }

    public void selectAuthMethod(byte opc) {
        // ASSUMING THAT AUTH METHODS IN THE DICTIONARY WILL BE ON THE SAME ORDER AS IN THE WEB PAGE
        String selectedAuthMethod = possibleAuthMethods.get(opc - 1);
        System.out.println("Se selecciona el método de autenticación " + selectedAuthMethod);
    }

    public void enterNIFandPINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        // Assuming auth method is Cl@ve PIN //
        citz.setNif(nif);  // We set the citizen nif to the one we got through parameter
        citz.setValDate(valDate);  // We set the citizen validation date to the one we got through parameter
        boolean res = authMethod.sendPIN(nif, valDate);
        if (res) {
            System.out.println("Se envia el PIN al usuario con DNI: " + nif.getNif());
        } else {
            throw new ConnectException("Error en l'enviament del SMS");
        }
    }

    public void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, ConnectException {
        boolean res = authMethod.checkPIN(citz.getNif(), pin);
        if (res) {
            System.out.println("El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent");

            if (selectedCertification != null) {
                switch (selectedCertification) {

                    case "Solicitar el informe de vida laboral" -> {
                        citz.setPDFDocument(administration.getLaboralLife(citz.getNif()));
                    }

                    case "Obtener acreditación del número de afiliación a la Seguridad Social" -> {
                        citz.setPDFDocument(administration.getMembAccred(citz.getNif()));
                    }
                }
            }
        } else {
            System.out.println("El PIN introduït no correspon al generat pel sistema per aquest ciutadà o ja no està vigent");
        }
    }

    public void enterCred(Nif nif, Password passwd) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        citz.setNif(nif);
        citz.setPassword(passwd);
        int res = authMethod.ckeckCredent(nif, passwd);
        switch (res) {
            case 0 -> throw new NifNotRegisteredException("El ciutadà no està registrat en el sistema Cl@u");
            case 1 -> {
                System.out.println("Les dades de l'usuari són correctes, no s'ha escollit el mètode reforçat");
            }
            case 2 -> {
                System.out.println("Les dades de l'usuari són correctes, s'ha escollit el mètode reforçat");
            }
        }
    }

    private void openDocument() throws BadPathException {
        openDocument(citz.getPDFDocument().getPath());
    }

    private void printDocument() throws BadPathException, PrintingException {
        printDocument(citz.getPDFDocument().getPath());
    }

    private void downloadDocument() throws BadPathException {
        downloadDocument(citz.getPDFDocument().getPath());
    }

    private void selectPath(DocPath path) throws BadPathException {
        System.out.println("Se ha seleccionado el path: " + path + " para guardar el documento");
    }

    // Other operations
    private String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String result = aapp.get(keyWord);
        if (result == null) throw new AnyKeyWordProcedureException("El tràmit buscat no és troba...");
        return result;
    }

    private void openDocument(DocPath path) throws BadPathException {
        try {
            citz.getPDFDocument().openDoc(path);
        } catch (IOException e) {
            throw new BadPathException("El path proporcionat no és valid");
        }
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {
        System.out.println("Se envia el documento para su impresión");
    }

    private void downloadDocument(DocPath path) throws BadPathException {
        System.out.println("Se descarga el documento");
    }

    // More operations

    public void injectAuthenticationMethod(CertificationAuthorityInterface method) {
        this.authMethod = method;
    }

    public void injectSS(SSInterface administration) {
        this.administration = administration;
    }

    // Optional - Digital Certificate
    public void selectCertificate(byte opc) {
        selectedCertification = possibleDigitalCertificates.get(opc - 1);
        System.out.println("Se ha seleccionado el certificado digital: " + selectedCertification);
    }

    public void enterPassw(Password pas) throws NotValidPasswordException, NotValidCertificateException, IOException, DecryptationException, WrongNifFormatException, NotAffiliatedException, WrongDocPathFormatException {
        if (pas == null) {
            throw new NotValidPasswordException("El password introduït no és valid, és null");
        }
        // If this line is called, the enterPIN from Cl@ve Permanente won't be, they're mutually exclusive
        citz.setPassword(pas);

        EncryptedData encryptedData = authMethod.sendCertfAuth(this.publicKey);
        Nif nif = decryptData(encryptedData);

        if (this.administration != null) {
            switch (this.selectedCertification) {

                case "Solicitar el informe de vida laboral" -> {
                    PDFDocument pdf = administration.getLaboralLife(nif);
                    pdf.openDoc(pdf.getPath());
                    System.out.println("Mostrant informe de la vida laboral...");
                }

                case "Obtener acreditación del número de afiliación a la Seguridad Social" -> {
                    PDFDocument pdf = administration.getMembAccred(nif);
                    pdf.openDoc(pdf.getPath());
                    System.out.println("Mostrant nombre d'acreditació de la SS...");

                }
            }
        }
    }

    private Nif decryptData(EncryptedData encrypdata) throws DecryptationException, WrongNifFormatException {
        System.out.println("Se envían para su desencriptación los datos");
        return Decryptor.decryptIDdata(encrypdata, this.privateKey);
    }
}
