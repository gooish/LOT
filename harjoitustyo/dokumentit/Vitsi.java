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
    
    private String laji;

    public Vitsi(int uusiTunniste, String uusiLaji, String uusiTeksti) {
        tunniste(uusiTunniste);
        laji(uusiLaji);
        teksti(uusiTeksti);
    }

    public void laji(String uusiLaji) throws IllegalArgumentException {
        if (uusiLaji != null && uusiLaji.length() > 0) {
            laji = uusiLaji;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String laji() {
        return laji;
    }

    // korvaukset 

    @Override
    public String toString() {
        return(Integer.toString(tunniste()) + "///" + laji + "///" + teksti());
    }
    
}