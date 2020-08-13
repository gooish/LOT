package harjoitustyo.dokumentit;
import harjoitustyo.dokumentit.Dokumentti;

/** Konkreettinen Vitsi-luokka, joka mallintaa dokumenttiluokkaa lisäten siihen tunnisteen ja muuta kivaa
 * <p>
 * Harjoitustyö, OOPE2 -kurssi.
 * <p>
 * @author Elli Mansikka (iivari.mansikka@tuni.fi)
 * Tampereen yliopisto
 */
public class Vitsi extends Dokumentti {
    
    /**
     * Vitsin laji
     */
    private String laji;

    /**
     * Vitsin rakentaja
     * @param uusiTunniste Vitsille haluttava tunniste
     * @param uusiLaji Vitsille haluttava laji
     * @param uusiTeksti Vitsille haluttava teksti
     */
    public Vitsi(int uusiTunniste, String uusiLaji, String uusiTeksti) {
        tunniste(uusiTunniste);
        laji(uusiLaji);
        teksti(uusiTeksti);
    }

    /**
     * Lajin asettava metodi
     * @param uusiLaji Uusi haluttava laji
     * @throws IllegalArgumentException mikäli laji on null tai liian lyhyt
     */
    public void laji(String uusiLaji) throws IllegalArgumentException {
        if (uusiLaji != null && uusiLaji.length() > 0) {
            laji = uusiLaji;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Vitsin lajin palauttava metodi
     * @return vitsin laji
     */
    public String laji() {
        return laji;
    }

    // korvaukset 

    /** 
     * Vitsin String esityksen palauttava metodi
     * @return Vitsin string-esitys
     */
    @Override
    public String toString() {
        return(Integer.toString(tunniste()) + "///" + laji + "///" + teksti());
    }
    
}