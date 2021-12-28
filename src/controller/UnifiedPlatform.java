package controller;

import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;
import exceptions.*;
import services.CertificationAuthority;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UnifiedPlatform {
    Citizen citz;

    HashMap<String, String> aapp;
    HashMap<String, ArrayList<String>> services;
    ArrayList<String> possibleAuthMethods;
    CertificationAuthority authMethod;

    public UnifiedPlatform() {
        this.citz = new Citizen();
        this.aapp = new HashMap<>();
        setAapp();
        this.services = new HashMap<>();
        setServices();
        this.possibleAuthMethods = new ArrayList<>();
        setAuthMethods();
    }

    private void setAapp() {
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
        // Override it with the new list
        services.put(key, values);
    }

    private void setAuthMethods() {
        possibleAuthMethods.add("Cl@ve PIN");
        possibleAuthMethods.add("Cl@ve Permanente");
        possibleAuthMethods.add("Certificado digital");

        // WE COULD ADD MORE METHODS //
    }

    public void processSearcher() {
        System.out.println("Es procedeix a usar el buscardor de tràmits");
        System.out.println("Es desplega el buscador");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String result = aapp.get(keyWord);
        if (result == null) throw new AnyKeyWordProcedureException("El tràmit buscat no és troba...");
        else {
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
        String selectedCertification = ssServices.get(opc);
        System.out.println("Se selecciona: " + selectedCertification);zº
    }

    public void selectAuthMethod(byte opc) {
        // ASSUMING THAT AUTH METHODS IN THE DICTIONARY WILL BE ON THE SAME ORDER AS IN THE WEB PAGE
        String selectedAuthMethod = possibleAuthMethods.get(opc);
        System.out.println("Se selecciona el método de autenticación " + selectedAuthMethod);
    }

    public void enterNIFandPINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        // Assuming auth method is Cl@ve PIN //
        citz.setNif(nif);  // We set the citizen nif to the one we got through parameter
        citz.setValDate(valDate);  // We set the citizen validation date to the one we got through parameter
        authMethod.sendPIN(nif, valDate);
        System.out.println("Se envia el PIN al usuario con DNI: " + nif);
    }

    public void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, ConnectException {
        boolean res = authMethod.checkPIN(citz.nif, pin);
        if (res) {
            System.out.println("El PIN introduït correspon al generat pel sistema per aquest ciutadà i encara està vigent");
        } else {
            System.out.println("El PIN introduït no correspon al generat pel sistema per aquest ciutadà o ja no està vigent");
        }
    }

    public void enterCred(Nif nif, Password passwd) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        if (!citz.getNif().equals(nif))
            throw new NifNotRegisteredException("El NIF introduit no és el que s'ha posat en passos anteriors");
        citz.setPassword(passwd);
        int res = authMethod.ckeckCredent(nif, passwd);
        switch (res) {
            case 0 -> throw new NifNotRegisteredException("El ciutadà no està registrat en el sistema Cl@u");
            case 1 -> {
                System.out.println("S'han introduït correctament les dades de l'usuari amb nif: " + nif);
                System.out.println("El usuari ha triat no usar el mètode d'autenticació no reforçat");
            }
            case 2 -> {
                System.out.println("S'han introduït correctament les dades de l'usuari amb nif: " + nif);
                System.out.println("El usuari ha triat usar el mètode d'autenticació no reforçat");
            }
        }
    }

    private void printDocument() throws BadPathException, PrintingException {
        System.out.println("Se envia el documento para su impresión");
    }

    private void downloadDocument() throws BadPathException, PrintingException {
        System.out.println("Se descarga el documento");
    }

    private void selectPath(DocPath path) throws BadPathException {
        citz.setSavePath(path);  // We set the citizen save path to the one we got through parameter
        System.out.println("Se ha seleccionado el path: " + path + " para guardar el documento");
    }
}
