package controller;

import controller.exceptions.*;
import controller.interfaces.UnifiedPlatformInterface;
import data.*;

import data.exceptions.NotValidCertificateException;
import data.exceptions.WrongNifFormatException;
import dummies.CertificadoDigitalCertificationAuthority;
import dummies.ClavePINCertificationAuthority;
import dummies.ClavePermanenteCertificationAuthority;
import dummies.SS;
import publicadministration.PDFDocument;
import services.interfaces.CertificationAuthorityInterface;
import services.Decryptor;
import services.interfaces.SSInterface;
import services.exceptions.DecryptationException;

import java.io.File;
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
    public HashMap<String, ArrayList<String>> services;
    public ArrayList<String> possibleAuthMethods;


    // Optional - Digital Certificate
    public String selectedAapp = null;
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
        System.out.println("[P] Es procedeix a usar el buscador de tràmits");
        System.out.println("[P] Es desplega el buscador");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String result = searchKeyWords(keyWord);
        selectedAapp = result;
        switch (result) {
            case "SS" -> System.out.println("[P] S'envia a mostrar l'AAPP: " + result);

            // In other cases

            case "AEAT" -> System.out.println("[P] Per ser implementat encara");

            case "MJ" -> System.out.println("[P] Per ser implementat encara");

            case "DGT" -> System.out.println("[P] Per ser implementat encara");

            // OTHER AAPP'S WOULD ALSO BE ADDED HERE //
        }
    }

    public void selectSS() {
        System.out.println("[P] Es fa click en la secció SS del mosaïc inicial");
    }

    public void selectCitizens() {
        System.out.println("[P] Es fa click en l'enllaç \"Ciudadanos\" de la secció de la SS");
    }

    public void selectReports() {
        System.out.println("[P] Es fa click en l'enllaç \"Informes y certificados\" de l'apartat \"Ciudadanos\" de la SS");
    }

    public void selectCertificationReport(byte opc) {
        // 0 is Laboral Life Doc
        // 1 SS affiliation number
        // We do this to acces through the dictionary
        // ASSUMING THAT SERCIVES IN THE DICTIONARY WILL BE IN THE SAME ORDER AS IN THE WEB PAGE
        // Since certifications are only available through SS, we asume citizen is using that AAPP
        ArrayList<String> ssServices = services.get("SS");
        this.selectedCertification = ssServices.get(opc - 1);
        System.out.println("[P] Es selecciona: " + selectedCertification);
    }

    public void selectAuthMethod(byte opc) {
        // ASSUMING THAT AUTH METHODS IN THE DICTIONARY WILL BE ON THE SAME ORDER AS IN THE WEB PAGE
        String selectedAuthMethod = possibleAuthMethods.get(opc - 1);
        System.out.println("[P] Es selecciona el mètode d'autenticació " + selectedAuthMethod);
    }

    public void enterNIFandPINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        // Assuming auth method is Cl@ve PIN //
        citz.setNif(nif);  // We set the citizen nif to the one we got through parameter
        citz.setValDate(valDate);  // We set the citizen validation date to the one we got through parameter
        boolean res = authMethod.sendPIN(nif, valDate);
        if (res) {
            System.out.println("[P] S'envia el PIN a l'usuari amb DNI: " + nif.getNif());
        } else {
            throw new ConnectException("Error en l'enviament del SMS");
        }
    }

    public void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, IOException, BadPathException {
        boolean res = authMethod.checkPIN(citz.getNif(), pin);
        if (res) {
            System.out.println("[P] El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent");

            if (administration != null) {
                switch (selectedCertification) {

                    case "Solicitar el informe de vida laboral" -> {
                        PDFDocument pdf = administration.getLaboralLife(citz.getNif());
                        citz.setPDFDocument(pdf);
                        pdf.openDoc(pdf.getPath());
                        System.out.println("[P] Mostrant informe de la vida laboral...");
                    }

                    case "Obtener acreditación del número de afiliación a la Seguridad Social" -> {
                        PDFDocument pdf = administration.getMembAccred(citz.getNif());
                        citz.setPDFDocument(pdf);
                        openDocument();
                        System.out.println("[P] Mostrant nombre d'acreditació de la SS...");
                    }
                }
            }
        } else {
            System.out.println("[P] El PIN introduït no correspon al generat pel sistema per aquest ciutadà o ja no està vigent");
        }
    }

    public void enterCred(Nif nif, Password passwd) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        citz.setNif(nif);
        citz.setPassword(passwd);
        int res = authMethod.ckeckCredent(nif, passwd);
        switch (res) {
            case 0 -> throw new NifNotRegisteredException("El ciutadà no està registrat en el sistema Cl@u");
            case 1 -> System.out.println("[P] Les dades de l'usuari són correctes, no s'ha escollit el mètode reforçat");

            case 2 -> System.out.println("[P] Les dades de l'usuari són correctes, s'ha escollit el mètode reforçat");

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
        if (!new File(path.getDocPath()).exists()) throw new BadPathException("El path especificat no existeix.");
        System.out.println("[P] S'ha seleccionat el path: " + path + " per a guardar el document");
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
        if (!new File(path.getDocPath()).exists()) throw new BadPathException("El path especificat no existeix.");
        System.out.println("[P] S'envia el document per a la seva impresió");
    }

    private void downloadDocument(DocPath path) throws BadPathException {
        if (!new File(path.getDocPath()).exists()) throw new BadPathException("El path especificat no existeix.");
        System.out.println("[P] Es descarrega el document");
    }

    // More operations

    public void injectAuthenticationMethod(CertificationAuthorityInterface method) {
        this.authMethod = method;
    }

    public void injectSS(SSInterface administration) {
        this.administration = administration;
    }

    public void getServiceFromString(String service) {
        switch (service) {
            case "SS" -> administration = new SS(citz);

            default -> System.out.println("[P] No hi ha més opcions de moment");
        }
    }

    public void getCertfAuthFromByte(byte opc, Citizen citizen) {
        switch (opc) {
            case 1 -> authMethod = new ClavePINCertificationAuthority(citizen);

            case 2 -> authMethod = new ClavePermanenteCertificationAuthority(citizen);

            case 3 -> authMethod = new CertificadoDigitalCertificationAuthority(citizen);

            default -> System.out.println("[P] No hi ha més opcions de moment");
        }
    }

    public void managePrintSave(byte opc) throws BadPathException, PrintingException {
        switch (opc) {
            case 1 -> printDocument();

            case 2 -> downloadDocument();
        }
    }

    // Optional - Digital Certificate
    public void selectCertificate(byte opc) {
        selectedCertification = possibleDigitalCertificates.get(opc - 1);
        System.out.println("[P] S'ha seleccionat el certificat digital: " + selectedCertification);
    }

    public void enterPassw(Password pas) throws NotValidPasswordException, NotValidCertificateException, IOException, DecryptationException, WrongNifFormatException, NotAffiliatedException, BadPathException {
        if (pas == null) {
            throw new NotValidPasswordException("El password introduït no és valid, és null");
        }
        // Si es crida aquesta línia, la de enterPIN de Cl@u Permanent no es cridarà, són mutuament excloents
        citz.setPassword(pas);

        EncryptedData encryptedData = authMethod.sendCertfAuth(this.publicKey);
        Nif nif = decryptData(encryptedData);
        citz.setNif(nif);

        if (this.administration != null) {
            switch (this.selectedCertification) {

                case "Solicitar el informe de vida laboral" -> {
                    PDFDocument pdf = administration.getLaboralLife(nif);
                    citz.setPDFDocument(pdf);
                    openDocument();
                    System.out.println("[P] Mostrant informe de la vida laboral...");
                }

                case "Obtener acreditación del número de afiliación a la Seguridad Social" -> {
                    PDFDocument pdf = administration.getMembAccred(nif);
                    citz.setPDFDocument(pdf);
                    openDocument();
                    System.out.println("[P] Mostrant nombre d'acreditació de la SS...");
                }
            }
        }
    }

    private Nif decryptData(EncryptedData encrypdata) throws DecryptationException, WrongNifFormatException {
        System.out.println("[P] S'envïen per a la seva desencriptació les dades");
        return Decryptor.decryptIDdata(encrypdata, this.privateKey);
    }
}
