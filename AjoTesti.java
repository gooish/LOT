import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.OmaLista;

/**
 * AjoTesti
 */
public class AjoTesti {

    public static void main(String[] args) {
        LinkedList<String> testiLista = new LinkedList<String>();
        OmaLista<String> intlist= new OmaLista<String>();
        testiLista.add("perse");
        testiLista.add("saatana");
        LocalDate joku = LocalDate.parse("2018-11-01"); 
        Uutinen testi = new Uutinen(22222, joku, "Perse");
        Uutinen vittu = new Uutinen(22222, joku, "Perse pillu saatana, homo. yeet tööt tuut.");
        System.out.println("uutisen teksti: " + testi.teksti());
        System.out.println("uutisen tunniste: " + testi.tunniste());
        System.out.println("uutisen pvä: " + testi.päivämäärä());
        System.out.println(testi.toString());
        if (testi.equals(vittu)) {
            System.out.println("on");
        }
        vittu.siivoa(testiLista, ".,");
        System.out.println(vittu.teksti());
        Scanner input = new Scanner(System.in);
        while (true) {
            intlist.lisää(input.nextLine());
            System.out.println(intlist.toString());

        }
    }


    
}