package harjoitustyo.kokoelma;

import java.util.Iterator;

import harjoitustyo.apulaiset.Kokoava;
import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.OmaLista;


/** Konkreettinen Kokoelma-luokka, joka sisältää dokumenttikokoelman,
 * ja erilaisia toimintoja sen kanssa toimimiseen
 * <p>
 * Harjoitustyö, OOPE2 -kurssi.
 * <p>
 * @author Elli Mansikka (iivari.mansikka@tuni.fi)
 * Tampereen yliopisto
 */

public class Kokoelma extends Object implements Kokoava<Dokumentti> {

    // attribuutit
    /**
     * Dokumentit sisältävä OmaLista
     */
    private OmaLista<Dokumentti> dokumentit;

    /**
     * OmaListan palauttava metodi
     * @return luokan dokumentit-OmaLista
     */
    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }

    /** 
     * Kokoelman rakentaja
     * 
     */
    public Kokoelma() {
        dokumentit = new OmaLista<Dokumentti>();
    }

    /**
     * Dokumentin lisäävä metodi
     * @param uusi kokoelmaan lisättävä dokumentti
     * @throws IllegalArgumentException mikäli se ei ole soveltuva
     */
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        if (uusi != null && uusi instanceof Comparable && hae(uusi.tunniste()) == null) {
            dokumentit.lisää(uusi);

        }
        else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Dokumenttia hakeva metodi
     * @param tunniste haettavan dokumentin tunniste
     * @return haettava dokumentti tai null
     */
    @Override
    public Dokumentti hae(int tunniste) {
        for (int i = 0; i < dokumentit.size(); i++) {
            if ((dokumentit.get(i).tunniste() == (tunniste))) {
                return dokumentit.get(i);
            }
        }
        return null;
    }

    /**
     * Dokumentin poistava metodi
     * @param tunniste poistettavan dokumentin tunniste
     * @return true jos onnistui, false jos epäonnistui
     */
    public boolean delet(int tunniste) {
        for (int i = 0; i < dokumentit.size(); i++) {
            if ((dokumentit.get(i).tunniste() == (tunniste))) {
                dokumentit.remove(i);
                return true;
            }
        }
        return false;
    }
}