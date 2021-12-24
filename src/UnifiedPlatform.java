import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;
import exceptions.*;

import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

public class UnifiedPlatform {
    HashMap<String, String> aapp;
    Hashtable<String, String> services;
    String chosenService = null;

    public UnifiedPlatform() {
        this.aapp = new HashMap<>();
        setAapp();
        this.services = new Hashtable<>();
        setServices();
    }

    private void setAapp() {
        // Seguridad Social (SS)
        aapp.put("Solicitar el informe de vida laboral", "SS");
        aapp.put("Obtenenr acreditación del número de afiliación a la Seguridad Social", "SS");

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
        aapp.put("SS", "Solicitar el informe de vida laboral");
        aapp.put("SS", "Obtenenr acreditación del número de afiliación a la Seguridad Social");

        // Agència Estatal de la Administración Tributaria (AEAT)
        aapp.put("Obtener datos fiscales", "AEAT");
        aapp.put("Tramitar el borrador de la declaración de la renta", "AEAT");

        // Ministerio de Justícia (MJ)
        aapp.put("Solicitar el certificado de nacimiento", "MJ");

        // Dirección General de Tráfico (DGT)
        aapp.put("Consultar los puntos asociados al carnet de conducir", "DGT");

        // WE COULD ADD MORE SERVICES //
    }

    public void processSearcher() {
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String result = aapp.get(keyWord);
        if (result == null) throw new AnyKeyWordProcedureException("El tràmit buscat no és troba...");
        else {
            switch (result) {
                case "SS":
                    selectSS();
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
        selectCitizens();
    }

    public void selectCitizens() {
        selectReports();
    }

    public void selectReports() {

    }

    public void selectCertificationReport(byte opc) {

    }

    public void selectAuthMethod(byte opc) {

    }

    public void enterNIFandPINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {

    }

    public void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, ConnectException {

    }

    public void enterCred(Nif nif, Password passwd) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {

    }

    private void printDocument() throws BadPathException, PrintingException {

    }

    private void downloadDocument() throws BadPathException, PrintingException {

    }

    private void selectPath(DocPath path) throws BadPathException {

    }
}
