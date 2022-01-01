import controller.Citizen;
import controller.UnifiedPlatform;
import controller.exceptions.AnyKeyWordProcedureException;
import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.WrongNifFormatException;
import data.exceptions.WrongPINCodeFormatException;
import data.exceptions.WrongPasswordFormatException;

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
            showMosaic(+);
        }
        switch (platform.selectedAapp) {
            case "SS" -> platform.selectSS();

            // In other cases

            case "AEAT" -> System.out.println("Per ser implementat encara");

            case "MJ" -> System.out.println("Per ser implementat encara");

            case "DGT" -> System.out.println("Per ser implementat encara");
        }
    }

    private void showMosaic(String message) {
        System.out.println("Per a accedir a Seguretat Social premi 1");

        
        System.out.println("Per a accedir a Seguretat Social premi 1");


        System.out.println("Per a accedir a Seguretat Social premi 1");


        System.out.println("Per a accedir a Seguretat Social premi 1");
    }
}
