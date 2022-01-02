import controller.Citizen;
import controller.UnifiedPlatform;
import controller.exceptions.*;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.NotValidCertificateException;
import data.exceptions.WrongNifFormatException;
import data.exceptions.WrongPINCodeFormatException;
import data.exceptions.WrongPasswordFormatException;
import services.exceptions.DecryptationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static UnifiedPlatform platform = new UnifiedPlatform();
    static Citizen citizen;

    public static void main(String[] args) throws WrongNifFormatException, WrongPINCodeFormatException, WrongPasswordFormatException, AnyKeyWordProcedureException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, BadPathException, PrintingException {
        citizen = setUpCitizen();

        startSearch();
    }

    private static Citizen setUpCitizen() throws WrongNifFormatException, WrongPINCodeFormatException, WrongPasswordFormatException {
        Citizen citizen = new Citizen();
        citizen.setNif(new Nif("12345678A"));
        citizen.setValDate(new Date());
        citizen.setPIN(new PINcode("123"));
        citizen.setPassword(new Password("contrasenya123$"));
        citizen.setPhoneNumber("123456789");
        // Suposarem que té el mètode reforçat activat
        citizen.setReinforcedPINActivated(true);

        return citizen;
    }

    private static void startSearch() throws AnyKeyWordProcedureException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        System.out.println("[UI] Desitja usar el buscador? [S/n]");
        String answer = scanner.nextLine();
        switch (answer) {
            case "S", "s" -> manageSearch();

            case "N", "n" -> showAAPP();

            default -> {
                System.out.println("[UI] Entrada no reconeguda, siusplau torni a intentar-ho.");
                startSearch();
            }
        }
    }

    private static void manageSearch() throws AnyKeyWordProcedureException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        platform.processSearcher();
        System.out.println("[UI] Introdueixi el tràmit a buscar");
        System.out.println("[UI] Ara per ara, només és suporta buscar tot el tràmit, tal i com surt en l'enunciat");
        String answer = scanner.nextLine();
        platform.enterKeyWords(answer);
        showAAPP();
    }

    private static void showAAPP() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        if (platform.selectedAapp == null) {
            showMosaic();
        }
        switch (platform.selectedAapp) {
            case "SS" -> {
                manageSS();
            }

            // In other cases

            case "AEAT" -> System.out.println("[UI] Per ser implementat encara");

            case "MJ" -> System.out.println("[UI] Per ser implementat encara");

            case "DGT" -> System.out.println("[UI] Per ser implementat encara");
        }
    }

    private static void showMosaic() {
        System.out.println("[UI] Per a accedir a la Seguretat Social premi 1");


        System.out.println("[UI] Per a accedir a l'Agència Estatal de Administración Tributária premi 2");


        System.out.println("[UI] Per a accedir al Ministeri de Justícia premi 3");


        System.out.println("[UI] Per a accedir a la Dirección General de Tráfico premi 4");

        manageMosaicAnswer();
    }

    private static void manageMosaicAnswer() {
        int answer = scanner.nextInt();
        switch (answer) {
            case 1 -> platform.selectedAapp = "SS";

            case 2 -> platform.selectedAapp = "AEAT";

            case 3 -> platform.selectedAapp = "MJ";

            case 4 -> platform.selectedAapp = "DGT";

            default -> {
                System.out.println("[UI] No s'ha detectat l'opció, torni a intentar-ho");
                showMosaic();
            }
        }
    }

    private static void manageSS() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        platform.getServiceFromString("SS");
        platform.selectSS();
        SSFirstLevel();
    }

    private static void SSFirstLevel() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        System.out.println("[UI] Per a entrar en la secció Ciutadans premi 1");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1 -> {
                platform.selectCitizens();
                SSSecondLevel();
            }

            default -> {
                System.out.println("[UI] No hi ha més opcions de moment");
                SSFirstLevel();
            }
        }
    }

    private static void SSSecondLevel() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        System.out.println("[UI] Per a entrar en la secció Informes y certificados premi 1");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1 -> {
                platform.selectReports();
                SSSelectReport();
            }

            default -> {
                System.out.println("[UI] No hi ha més opcions de moment");
                SSSecondLevel();
            }
        }
    }

    private static void SSSelectReport() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        ArrayList<String> possibleReports = platform.services.get("SS");
        for (int i = 0; i < possibleReports.size(); i++) {
            System.out.println("[UI] Per a seleccionar " + possibleReports.get(i) + " premi " + (i + 1));
        }
        byte answer = scanner.nextByte();
        platform.selectCertificationReport(answer);
        selectAuthMethod();
    }

    private static void selectAuthMethod() throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException, PrintingException {
        for (int i = 0; i < platform.possibleAuthMethods.size(); i++) {
            System.out.println("[UI] Per a autenticar-te amb " + platform.possibleAuthMethods.get(i) + " premi " + (i + 1));
        }
        byte answer = scanner.nextByte();
        platform.selectAuthMethod(answer);
        platform.getCertfAuthFromByte(answer, citizen);
        manageAuth(answer);
        manageFinalOptions();
    }

    private static void manageAuth(byte answer) throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException {
        switch (answer) {
            case 1 -> {  // Cl@ve PIN
                platform.setTelephoneNumber(citizen.getPhoneNumber());
                platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
                platform.enterPIN(citizen.getPIN());
            }

            case 2 -> {  // Cl@ve Permanente
                platform.enterCred(citizen.getNif(), citizen.getPassword());
                // If citizen has reinforced the reinforced PIN activated, enter its PIN
                if (citizen.hasReinforcedPINActivated()) {
                    platform.setTelephoneNumber(citizen.getPhoneNumber());
                    platform.enterPIN(citizen.getPIN());
                }
            }

            case 3 -> {  // Certificado digital
                platform.enterPassw(citizen.getPassword());
            }

            default -> {
                System.out.println("[UI] No hi ha més opcions de moment");
                manageAuth(answer);
            }
        }
    }

    private static void manageFinalOptions() throws BadPathException, PrintingException {
        System.out.println("[UI] Certificat generat, que desitja fer amb ell?");
        System.out.println("[UI] Per a imprimir el certificat, premi 1");
        System.out.println("[UI] Per a descarregar el certificat, premi 2");
        byte answer = scanner.nextByte();
        if (answer != 1 && answer != 2) {
            System.out.println("[UI] Nombre incorrecte");
            manageFinalOptions();
        }
        platform.managePrintSave(answer);
    }
}
