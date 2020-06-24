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
    private OmaLista<Dokumentti> dokumentit;

    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }

    public Kokoelma() {
        dokumentit = new OmaLista<Dokumentti>();
    }

    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        if (uusi != null && uusi instanceof Comparable && hae(uusi.tunniste()) == null) {
            dokumentit.lisää(uusi);

        }
        else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Dokumentti hae(int tunniste) {
        for (int i = 0; i < dokumentit.size(); i++) {
            if ((dokumentit.get(i).tunniste() == (tunniste))) {
                return dokumentit.get(i);
            }
        }
        return null;
    }

    public boolean delet(int tunniste) {
        for (int i = 0; i < dokumentit.size(); i++) {
            if ((dokumentit.get(i).tunniste() == (tunniste))) {
                dokumentit.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean teeIdSort() {
        OmaLista<Dokumentti> valiLista = new OmaLista<Dokumentti>();
        for (Dokumentti dokumentti : dokumentit) {
            valiLista.lisää(dokumentti);
        }
        dokumentit = valiLista;
        return true;
    }
     
    /*
    public boolean teeDateSort() {
        OmaLista<Dokumentti> valiLista = new OmaLista<Dokumentti>();
        for (Dokumentti dokumentti : dokumentit) {
            valiLista.lisääByDate(dokumentti);
        }
        dokumentit = valiLista;
        return true;

    }
    */
}