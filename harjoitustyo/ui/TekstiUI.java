package harjoitustyo.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;
import harjoitustyo.shell.LOTShell;



/** Konkreettinen TekstiUI-luokka, joka sisältää ohjelman 
 * pääsilmukan ja muun sellaisen ohjelman alueen, jonka kanssa käyttäjä interaktoi suoraan
 * <p>
 * Harjoitustyö, OOPE2 -kurssi.
 * <p>
 * @author Elli Mansikka (iivari.mansikka@tuni.fi)
 * Tampereen yliopisto
 */

public class TekstiUI {

    // Luokkavakiot komennoille, jotta niit voi muuttaaa iisisti

    final String PRINT = "print";
    final String ADD = "add";
    final String QUERY = "find";
    final String REMOVE = "remove";
    final String FILTER = "polish";
    final String RETURN = "reset";
    final String FREQUENCIES = "freqs";
    final String SORT = "sort";
    final String PRETTYPRINT = "pprint";
    final String ECHO = "echo";
    final String EXIT = "quit";
    final String ERROR = "Error!";





    private String[] args;

    Scanner input = new Scanner(System.in);

    /**
     * Rakentaja TekstiUI -oliolle
     * @param newArgs tekstiUI:n argumentit
     * @throws IllegalArgumentException mikäli argumentteja on liian vähän tai paljon
     */
    public TekstiUI(String[] newArgs) throws IllegalArgumentException {
        if (newArgs.length == 2) {
            args = newArgs;
        } else {
            System.out.println("Welcome to L.O.T.");
            System.out.println("Wrong number of command-line arguments!");
            System.out.println("Program terminated.");
            System.exit(0);
        }
    }


    /**
     * Ohjelman pääsilmukka
     */
    public void start() {
        System.out.println("Welcome to L.O.T.");
        String syote;
        String[] komento;
        boolean jatketaanko = true;
        boolean echo = false;

        LOTShell kuori = new LOTShell();
        try {
            kuori.lataa(args[0]);
            kuori.hankiSulkusanat(args[1]);
        } catch (FileNotFoundException e) {
            jatketaanko = false;
        }



        while (jatketaanko) {

            System.out.println("Please, enter a command:");


            syote = input.nextLine();
            if (echo == true) {
                System.out.println(syote);
            }
            komento = syote.split(" ");
            try {
                switch (komento[0]) {

                    case PRINT:
                        if (komento.length == 2) {
                            kuori.print(Integer.parseInt(komento[1]));
                        }
                        else if (komento.length == 1) {
                            kuori.printAll();
                        }
                        else {
                            System.out.println(ERROR);
                        }
                        break;

                    case ADD:
                        if (komento.length > 1) {
                            kuori.lisaa(syote.substring(4));
                        }
                        else {
                            System.out.println(ERROR);
                        }
                        break;

                    case QUERY:
                        if (komento.length > 1) {
                            String[] etsittavat = syote.substring(5).split(" ");
                            kuori.etsi(etsittavat);
                        }
                        else {
                            System.out.println(ERROR);
                        }
                        break;

                    case REMOVE:
                        if (komento.length == 2) {
                            if(!kuori.freqs(Integer.parseInt(komento[1]))) {
                                System.out.println(ERROR);
                            }
                        }
                        else {
                            System.out.println(ERROR);
                        }
                        break;

                    case FILTER:
                        if (komento.length == 2) {
                            kuori.suodata(komento[1]);
                        }
                        else {
                            System.out.println(ERROR);
                        }
                        break;

                    case RETURN:
                        kuori.resetoi(args[0]);
                        break;

                    case FREQUENCIES:
                        kuori.freqs(Integer.parseInt(komento[1]));
                        break;

                    case SORT:
                        kuori.sorttaa(komento[1]);
                        break;

                    case PRETTYPRINT:
                        kuori.pprint(Integer.parseInt(komento[1]));
                        break;

                    case EXIT:
                        jatketaanko = false;
                        break;

                    case ECHO:
                        echo = true;
                        System.out.println("echo");
                        break;

                    default:
                        System.out.println(ERROR);
                        break;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(ERROR);
            }
        }

        System.out.println("Program terminated.");
    }
}