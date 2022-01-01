import controller.Citizen;
import controller.UnifiedPlatform;
import controller.exceptions.AnyKeyWordProcedureException;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.WrongNifFormatException;
import data.exceptions.WrongPINCodeFormatException;
import data.exceptions.WrongPasswordFormatException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static UnifiedPlatform platform = new UnifiedPlatform();
    static Citizen citizen;

    public static void main(String[] args) throws WrongNifFormatException, WrongPINCodeFormatException, WrongPasswordFormatException, AnyKeyWordProcedureException {
        citizen = setUpCitizen();

        wantToSearch();
    }

    private static Citizen setUpCitizen() throws WrongNifFormatException, WrongPINCodeFormatException, WrongPasswordFormatException {
        Citizen citizen = new Citizen();
        citizen.setNif(new Nif("12345678A"));
        citizen.setValDate(new Date());
        citizen.setPIN(new PINcode("123"));
        citizen.setPassword(new Password("contrasenya123$"));

        return citizen;
    }

    private static void wantToSearch() throws AnyKeyWordProcedureException {
        System.out.println("Desitja usar el buscador? [S/n]");
        String answer = scanner.nextLine();
        switch (answer) {
            case "S", "s" -> manageSearch();

            case "N", "n" -> showAAPP();

            default -> {
                System.out.println("Entrada no reconeguda, siusplau torni a intentar-ho.");
                wantToSearch();
            }
        }
    }

    private static void manageSearch() throws AnyKeyWordProcedureException {
        platform.processSearcher();
        System.out.println("Introdueixi el tràmit a buscar");
        System.out.println("Ara per ara, només és suporta buscar tot el tràmit, tal i com surt en l'enunciat");
        String answer = scanner.nextLine();
        platform.enterKeyWords(answer);
        showAAPP();
    }

    private static void showAAPP() {
        if (platform.selectedAapp == null) {
            showMosaic();
        }
        switch (platform.selectedAapp) {
            case "SS" -> {
                manageSS();
            }

            // In other cases

            case "AEAT" -> System.out.println("Per ser implementat encara");

            case "MJ" -> System.out.println("Per ser implementat encara");

            case "DGT" -> System.out.println("Per ser implementat encara");
        }
    }

    private static void showMosaic() {
        System.out.println("Per a accedir a la Seguretat Social premi 1");


        System.out.println("Per a accedir a l'Agència Estatal de Administración Tributária premi 2");


        System.out.println("Per a accedir al Ministeri de Justícia premi 3");


        System.out.println("Per a accedir a la Dirección General de Tráfico premi 4");

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
                System.out.println("No s'ha detectat l'opció, torni a intentar-ho");
                showMosaic();
            }
        }
    }

    private static void manageSS() {
        platform.selectSS();
        SSFirstLevel();
    }

    private static void SSFirstLevel() {
        System.out.println("Per a entrar en la secció Ciutadans premi 1");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1 -> {
                platform.selectCitizens();
                SSSecondLevel();
            }

            default -> {
                System.out.println("No hi ha més opcions de moment");
                SSFirstLevel();
            }
        }
    }

    private static void SSSecondLevel() {
        System.out.println("Per a entrar en la secció Informes y certificados premi 1");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1 -> {
                platform.selectReports();
                SSSelectReport();
            }

            default -> {
                System.out.println("No hi ha més opcions de moment");
                SSSecondLevel();
            }
        }
    }

    private static void SSSelectReport() {
        ArrayList<String> possibleReports = platform.services.get("SS");
        for (int i = 0; i < possibleReports.size(); i++) {
            System.out.println("Per a seleccionar " + possibleReports.get(i) + " premi " + (i + 1));
        }
        byte answer = scanner.nextByte();
        platform.selectCertificationReport(answer);
        selectAuthMethod();
    }

    private static void selectAuthMethod() {
        for (int i = 0; i < platform.possibleAuthMethods.size(); i++) {
            System.out.println("Per a autenticar-te amb " + platform.possibleAuthMethods.get(i) + " premi " + (i + 1));
        }
        byte answer = scanner.nextByte();
        platform.selectAuthMethod(answer);
    }
}
