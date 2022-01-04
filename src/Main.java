import controller.Citizen;
import controller.UnifiedPlatform;
import controller.exceptions.*;
import data.AccredNumb;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.*;
import dummies.SS;
import services.exceptions.DecryptationException;
import services.interfaces.SSInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static UnifiedPlatform platform = new UnifiedPlatform();
    static Citizen citizen;

    public static void main(String[] args) throws WrongNifFormatException, WrongPINCodeFormatException, WrongPasswordFormatException, AnyKeyWordProcedureException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, BadPathException, PrintingException, WrongAccredNumbFormatException {
        System.out.println("[UI] Vols introduir tu les dades o utilitzar un ciutadà que té les dades correctes?");
        System.out.println("[UI] Per a introduir-les vosté premi 1");
        System.out.println("[UI] Per usar el ciutadà d'exemple premi 2");
        int answer = scanner.nextInt();
        scanner.nextLine(); // To get rid of \n
        switch (answer) {
            case 1 -> citizen = setUpPersonalCitizen();


            case 2 -> citizen = setUpDefaultCitizen();

            default -> {
                System.out.println("Opció no reconeguda, si us plau torna a intentar-ho");
                main(args);
            }
        }

        startSearch();
        System.exit(0);
    }

    private static Citizen setUpPersonalCitizen() throws WrongNifFormatException, WrongPINCodeFormatException, WrongPasswordFormatException, WrongAccredNumbFormatException {
        System.out.println("A continuació, per a fer-ho més fàcil, et demanarem totes les dades que potser necessitem després");
        Citizen citizen = new Citizen();
        System.out.println("[UI] Introdueix el teu NIF");
        citizen.setNif(new Nif(scanner.nextLine()));
        citizen.setValDate(getPersonalCitizenDate());
        System.out.println("[UI] Introdueix el teu PIN");
        citizen.setPIN(new PINcode(scanner.nextLine()));
        System.out.println("[UI] Introdueix la teva contrasenya");
        citizen.setPassword(new Password(scanner.nextLine()));
        System.out.println("[UI] Introdueix el teu número de telèfon");
        citizen.setPhoneNumber(scanner.nextLine());
        System.out.println("[UI] Introdueix el teu número de acreditació de la SS");
        citizen.setAccredNumb(new AccredNumb(scanner.nextLine()));
        setReinforced(citizen);


        return citizen;
    }

    private static Date getPersonalCitizenDate() {
        System.out.println("[UI] Introdueix la data de caducitat d'aquest en el format mm/yy");
        String dateString = scanner.nextLine();
        char[] c = dateString.toCharArray();
        if (!correctDateFormat(c)) {
            System.out.println("[UI] Error, torna a intentar-ho");
            getPersonalCitizenDate();
        } else {
            Calendar cal = Calendar.getInstance();

            int firstYearUnit = c[3];
            int secondYearUnit = c[4];
            int year = 2000 + firstYearUnit * 10 + secondYearUnit;

            int firstMonthUnit = c[3];
            int secondMonthUnit = c[4];
            int month = firstMonthUnit * 10 + secondMonthUnit;
            cal.set(year, month, 31);
            return cal.getTime();
        }
        return null;
    }

    private static boolean correctDateFormat(char[] date) {
        // Bad format
        if (date.length != 5) {
            return false;
        }
        // If month > 12, '0' is to parse to integer
        int firstMonthUnit = date[0] - '0';
        int secondMonthUnit = date[1] - '0';
        if ((firstMonthUnit * 10 + secondMonthUnit) > 12) {
            return false;
        }

        // If year < current year, '0' is to parse to integer
        int firstYearUnit = date[3] - '0';
        int secondYearUnit = date[4] - '0';
        int currentYear = 1900 + new Date().getYear();
        int enteredYear = 2000 + (firstYearUnit * 10 + secondYearUnit);

        return currentYear <= enteredYear;
    }

    private static void setReinforced(Citizen setUpCitz) {
        System.out.println("[UI] En cas de que usis Cl@u Permanent, vols usar el mètode reforçat? [S/n]");
        switch (scanner.nextLine()) {
            case "S", "s" -> setUpCitz.setReinforcedPINActivated(true);

            case "N", "n" -> setUpCitz.setReinforcedPINActivated(false);

            default -> {
                System.out.println("Error, torna a intentar-ho");
                setReinforced(setUpCitz);
            }
        }
    }

    private static Citizen setUpDefaultCitizen() throws WrongNifFormatException, WrongPINCodeFormatException, WrongPasswordFormatException, WrongAccredNumbFormatException {
        Citizen citizen = new Citizen();
        citizen.setNif(new Nif("12345678A"));
        citizen.setValDate(new Date());
        citizen.setPIN(new PINcode("123"));
        citizen.setPassword(new Password("contrasenya123$"));
        citizen.setPhoneNumber("123456789");
        citizen.setAccredNumb(new AccredNumb("123456789"));
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
            case "SS" -> manageSS();


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

        // Just for visual purposes, we must set the accreditation number to get it due to double classes
        // When chosen accreditation document, we set the accreditation number to the one granted by the user
        System.out.println("DEBUG " + possibleReports.get(1));
        if (answer == (byte) 2) {
            SS admin = (SS) platform.administration;
            admin.setAccredNumb(citizen.getAccredNumb());
        }

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

    private static void manageAuth(byte answer) throws IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, NotValidPINException, NotAffiliatedException, NotValidCredException, NotValidPasswordException, NotValidCertificateException, DecryptationException, WrongNifFormatException, BadPathException {
        switch (answer) {
            case 1 -> {  // Cl@ve PIN
                platform.enterNIFandPINobt(citizen.getNif(), citizen.getValDate());
                platform.enterPIN(citizen.getPIN());
            }

            case 2 -> {  // Cl@ve Permanente
                platform.enterCred(citizen.getNif(), citizen.getPassword());
                // If citizen has reinforced the reinforced PIN activated, enter its PIN
                if (citizen.hasReinforcedPINActivated()) {
                    platform.enterPIN(citizen.getPIN());
                }
            }

            case 3 -> platform.enterPassw(citizen.getPassword());  // Certificado Digital

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
