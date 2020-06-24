package harjoitustyo.dokumentit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import harjoitustyo.dokumentit.Dokumentti;

/** Konkreettinen Uutinen-luokka, joka mallintaa dokumenttiluokkaa ja
 * lisää siihen päivämäärän ja sellaista
 * <p>
 * Harjoitustyö, OOPE2 -kurssi.
 * <p>
 * @author Elli Mansikka (iivari.mansikka@tuni.fi)
 * Tampereen yliopisto
 */
public class Uutinen<E> extends Dokumentti<E> {

    private LocalDate päivämäärä;

    // rakentaja

    public Uutinen (int uusiTunniste, LocalDate uusiPva, String uusiUutinen) throws IllegalArgumentException {
        tunniste(uusiTunniste);
        päivämäärä(uusiPva);
        teksti(uusiUutinen);

    }

    public void päivämäärä(LocalDate uusiPvm) throws IllegalArgumentException {
        if (uusiPvm != null) {
            päivämäärä = uusiPvm;
        }
        else {
           throw new IllegalArgumentException();
        }
    }

    public LocalDate päivämäärä() {
    
        return päivämäärä;

    }
    @Override
    public String toString() {
        DateTimeFormatter formatoija = DateTimeFormatter.ofPattern("d.M.YYYY"); 
        return(Integer.toString(tunniste()) + "///" + formatoija.format(päivämäärä) + "///" + teksti());
    }
}