package harjoitustyo.dokumentit;

import harjoitustyo.apulaiset.Tietoinen;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/** Abstrakti Dokumentti-luokka, jonka tarkoitus on attribuoida kaikenlaista
 * korpukseen lisättäviin dokumentteihin liittyen
 * <p>
 * Harjoitustyö, OOPE2 -kurssi.
 * <p>
 * @author Elli Mansikka (iivari.mansikka@tuni.fi)
 * Tampereen yliopisto
 */
public abstract class Dokumentti<E> implements Comparable<Dokumentti>, Tietoinen<Dokumentti> {

    // attribuutit

    /**
     * dokumentin tunniste, k'ytet''n sen yksil;intiin
     */
    private int tunniste;

    /**
     * dokumentin teksti, se mit' varten se olemassa on
     */
    private String teksti;

/**
 * tunnisteen asettava metodi
 * @param uusiTunniste tunniste, joka metodille annetaan
 * @throws IllegalArgumentException jos tunniste on alle ykk;sen
 */
    public void tunniste(int uusiTunniste) throws IllegalArgumentException {
        if (uusiTunniste > 0) {
            tunniste = uusiTunniste;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /** Tunnisteen palauttava metodi
     * @return dokumentin tunniste
     */
    public int tunniste() {
        return tunniste;
    }

    /**
 * Tekstin asettava metodi
 * @param uusiTeksti teksti, joka annetaan
 * @throws IllegalArgumentException jos teksti on liian lyhyt
 */
    public void teksti(String uusiTeksti) throws IllegalArgumentException {
        if (uusiTeksti != null && uusiTeksti.length() > 0) {
            teksti = uusiTeksti;
        } else {
            throw new IllegalArgumentException();
        }
    }
    /** Tekstin palauttava metodi
     * @return dokumentin Teksti
     */
    public String teksti() {
        return teksti;
    }

    public Dokumentti() throws IllegalArgumentException {
    }

    public Dokumentti(int uusiTunniste, String uusiTeksti) throws IllegalArgumentException {
        tunniste(uusiTunniste);
        teksti(uusiTeksti);
    }

    
    /** Dokumentin equals-metodi, tarkistaa onko kaksi dokumenttia samat
     * @return true jos ovat, false jos eivät
     * 
     */

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (o instanceof Dokumentti) {
            if (((Dokumentti) o).tunniste == this.tunniste) {
                return true;
            }

        }
        return false;
    }


    /** Implementoitu toString -metodi
     * kertoo, miten tulostaa dokumentti.'
     * @return dokumentin String -esitys
     */
    @Override
    public String toString() {
        return (tunniste + "///" + teksti);
    }

    /** implementoitu compareTo -metodi
     * kertoo, tulisiko dokumentin tulla ennen vai jälkeen toista järjestyksessä.
     * @return suuruusarvo
     */


    @Override
    public int compareTo(Dokumentti that) {
        if (tunniste > that.tunniste()) {
            return 1;
        } else if (tunniste == that.tunniste()) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
    * Katsoo tasmaako mikaan sisaan annetuista sanoista 
    * poistettavia sanoja tai jtn
    * <p>
    * @param hakusanat sanat, joiden tasmaavyytta korpukseen tarkistetaan
    * @return tasmaavatko sanat dokumenttiin
    */   

    public boolean sanatTäsmäävät(LinkedList<String> hakusanat) throws IllegalArgumentException {
        if (hakusanat != null && hakusanat.size() > 0) {
            boolean onkoYksittäinen = false;
            boolean onkoKaikki = true;
            String[] kaikkiListanSanat = teksti.split(" ");
            for (Iterator i = hakusanat.iterator(); i.hasNext();) {
                onkoYksittäinen = false;
                String haettavaSana = i.next().toString();
                for (int j = 0; j < kaikkiListanSanat.length; j++) {
                    if (haettavaSana.equals(kaikkiListanSanat[j])) {
                        onkoYksittäinen = true;
                    }
                }
                if (onkoYksittäinen == false) {
                    onkoKaikki = false;
                }
            }
            return onkoKaikki;
        } else {
            throw new IllegalArgumentException();
        }

    }


    /**
     * Siivoa-metodi, poistaa kaikista teksteist' sellaiset sanat jotka kuuluvat sulkusanalistaan ja sellaiset merkit jotka kuuluvat v'limerkkeihin
     * <p>
     * @param välimerkit merkit, jotka halutaan poistettavaksi ehdotta
     * <p>
     * @param sulkusanat sanat, jotka poistetaan, jos niit' ymp'r;i v'lily;nti.
     */
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit) throws IllegalArgumentException {
        if (sulkusanat != null && välimerkit != null && välimerkit.length() > 0 && sulkusanat.size() > 0) {
            String[] merkarray = välimerkit.split("");
            for (String merkki : merkarray) {
                if (teksti.contains(merkki)) {
                    teksti = teksti.replace(merkki, "");
                }
            }

            teksti = teksti.replace("  ", " ");
            List<String> mistaPoistetaan = new LinkedList<String>(Arrays.asList(teksti.toLowerCase().split(" ")));
            mistaPoistetaan.removeAll(sulkusanat);
            // teksti = mistaPoistetaan.toString().replace(",", "").replace("[", "").replace("]", "").trim();

            teksti = " ";
            for (String sana : mistaPoistetaan) {
                teksti = teksti + sana + " ";
            }
            teksti = teksti.trim();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}