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

    /**
     * Rakentaja uutiselle
     * @param uusiTunniste uutiselle haluttava tunniste
     * @param uusiPva uutiselle haluttava päivämäärä
     * @param uusiUutinen uutiselle haluttava teksti
     * @throws IllegalArgumentException jos mikä tahansa asetusmetodi epäonnistuu
     */
    public Uutinen (int uusiTunniste, LocalDate uusiPva, String uusiUutinen) throws IllegalArgumentException {
        tunniste(uusiTunniste);
        päivämäärä(uusiPva);
        teksti(uusiUutinen);

    }

    /**
     * Asettaa uuden päivämäärän
     * @param uusiPvm uusi haluttava päivämäärä
     * @throws IllegalArgumentException mikäli päivämäärä on null
     */
    public void päivämäärä(LocalDate uusiPvm) throws IllegalArgumentException {
        if (uusiPvm != null) {
            päivämäärä = uusiPvm;
        }
        else {
           throw new IllegalArgumentException();
        }
    }

    /**
     * päivämäärän hakeva metodi
     * @return uutisen päivämäärän
     */
    public LocalDate päivämäärä() {
    
        return päivämäärä;

    }

    /** 
     * uutisen String esityksen palauttava metodi
     * @return Uutisen string-esitys
     */
    @Override
    public String toString() {
        DateTimeFormatter formatoija = DateTimeFormatter.ofPattern("d.M.YYYY"); 
        return(Integer.toString(tunniste()) + "///" + formatoija.format(päivämäärä) + "///" + teksti());
    }
}