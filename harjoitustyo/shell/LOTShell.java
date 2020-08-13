package harjoitustyo.shell;

import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.kokoelma.Kokoelma;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * Konkreettinen LOTShell-luokka, joka käsittelee komennot ja muut vastaavat,
 * jotka on esitetty sen metodeina
 * <p>
 * Harjoitustyö, OOPE2 -kurssi.
 * <p>
 * 
 * @author Elli Mansikka (iivari.mansikka@tuni.fi) Tampereen yliopisto
 */

public class LOTShell {

    // luodaan luokkamuuttujat

    /**Kokoelma, jonne vitsit menevät */
    private Kokoelma korpus = new Kokoelma();

    /** LinkedList, jonne sulkusanat menevät */
    private LinkedList<String> sulkusanat = new LinkedList<String>();

    Scanner input = new Scanner(System.in);

    /** Dokumentin tyyppiä kuvaava muuttuja */
    private static int dokTyyppi = 0;

    /*
     * Paasilmukasta kutsuttavia metodeja
     *
     * ne kayttavat pitkalti muualta loytyvia metodeja
     *
     * Nama ovat suorian toimintoja. naille voi implementoida minka vain
     * kayttoliittyman
     */

    /**
     * tulostuskomento, hakee korpuksesta dokumentin ja tulostaa sen
     * <p>
     * 
     * @param mika tulostettavan dokumentin numero
     *             <p>
     * @return Onnistuiko operaatio vai ei
     *         <p>
     * @throws IllegalArgumentException, jos haettavaa vastaavaa ei löydy.
     */
    public boolean print(int mika) throws IllegalArgumentException {
        if (korpus.hae(mika) != null) {
            System.out.println(korpus.hae(mika));
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 
     * lisayskomento, lisaa kokoelman lisayskomennolla kokoelmaan dokumentin
     * 
     * @param lisattava minkälaisilla teksteillä olio lisätään korpukseen
     * @return sen perusteella onnistuiko
     * @throws IllegalArgumentException jos se saa sellaisen kutsumaltaan metodilta
     */

    public boolean lisaa(String lisattava) throws IllegalArgumentException {
        korpus.lisää(teeOlio(lisattava));
        return true;
    }

    /**
     * 
     * etsii kokoelmasta dokumentteja kayttaen kokoelman metodeja
     * 
     * @param etsittavat ne mitä etsitään
     * @return sen perusteella onnistuiko
     */

    public boolean etsi(String[] etsittavat) {
        boolean onkoSiella = true;
        LinkedList<String> etsiLista = new LinkedList(Arrays.asList(etsittavat));
        for (int i = 0; i < korpus.dokumentit().size(); i++) {
            if (korpus.dokumentit().get(i).sanatTäsmäävät(etsiLista)) {
                System.out.println(korpus.dokumentit().get(i).tunniste());
            }
        }
        return false;
    }

    /**
     * Poistaa kokoelmasta dokumentteja
     * 
     * @param poistettava se mitä poistetaan
     * @return sen perusteella onnistuiko
     */
    public boolean poista(int poistettava) {

        return korpus.delet(poistettava);
    }

    /**
     * suodata kokoelman jokaisesta dokumentista sulkusanat ja valimerkit
     * 
     * @param suodatettava se mitä suodatetaan
     * @return sen perusteella onnistuiko
     */

    public boolean suodata(String suodatettava) {
        for (int i = 0; i < korpus.dokumentit().size(); i++) {
            korpus.dokumentit().get(i).siivoa(sulkusanat, suodatettava);
        }
        return true;
    }

    /**
     * lataa kokoelma uudelleen
     * 
     * @param tiedNimi se tiedosto mistä luetaan
     * @return sen perusteella onnistuiko
     */

    public boolean resetoi(String tiedNimi) {
        try {
            korpus.dokumentit().clear();
            lataa(tiedNimi);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }


    /**
     * Poista korpuksesta dokumentti
     * @param tunniste poistettavan tunniste
     * @return onnistuiko
     */
    public boolean freqs(int tunniste) {
        for (int i = 0; i < korpus.dokumentit().size(); i++) {
            if ((korpus.dokumentit().get(i).tunniste() == (tunniste))) {
                korpus.dokumentit().remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Stubattu
     * @param rivinPituus
     * @return
     */
    // TODO: toteuta prettyprinttaus
    public boolean pprint(int rivinPituus) {
        System.out.println("prettyprintataan");
        return false;
    }


    /**
     * Stubattu
     * @param sortType
     * @return
     */
    // TODO: toteuta kokoelman jarkkays
    public boolean sorttaa(String sortType) {
        System.out.println("sortataan");
        return true;
    }


    /**
     * Printtaa kaikki dokumentit
     * @return onnistuiko
     */
    public boolean printAll() {
        for (Dokumentti i : korpus.dokumentit()) {
            System.out.println(i);
        }
        return true;
    }

    /*
     * Paasilmukan metodien apumetodit ynna muut kuoren metodit
     *
     * kaytetaan tiedostojen lukuun ym. vastaavaan
     */

    // lataa tiedostosta dokumentit korpukseen

    /**
     * Tiedoston avaava metodi
     * @param tiedNimi halutun tiedoston tiedostonimi levyllä
     * @return onnistuiko
     * @throws FileNotFoundException mikäli tiedostoa ei löydy
     */
    public boolean lataa(String tiedNimi) throws FileNotFoundException {
        try {
            File tiedosto = new File(tiedNimi);
            Scanner tiedLukija = new Scanner(tiedosto);
            while (tiedLukija.hasNextLine()) {

                korpus.lisää(teeOlio(tiedLukija.nextLine()));
            }
            tiedLukija.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Missing file!");
            throw new FileNotFoundException();
        }
    }

    // Luo rivista olio, tyypista riippuen joko uutinen tai vitsi

    /**
     * Paloittelee rivin olioksi
     * @param rivi haluttava String-muotoinen tekstinpätkä
     * @return Tämä tekstinpätkä oliona
     * @throws IllegalArgumentException mikäli muunnos ei onnistu
     */
    static Dokumentti teeOlio(String rivi) throws IllegalArgumentException {
        String[] ominaisuudet = rivi.split("///");
        String[] pvmTaiID = ominaisuudet[1].split("\\.");

        if (pvmTaiID.length == 1) {
            if (dokTyyppi == 1 || dokTyyppi == 0) {
                Vitsi ulosMenevaVitsi = new Vitsi(Integer.parseInt(ominaisuudet[0]), ominaisuudet[1], ominaisuudet[2]);
                dokTyyppi = 1;
                return ulosMenevaVitsi;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            if (dokTyyppi == 2 || dokTyyppi == 0) {
                dokTyyppi = 2;
                LocalDate uutisenPvm = LocalDate.of(Integer.parseInt(pvmTaiID[2]), Integer.parseInt(pvmTaiID[1]),
                        Integer.parseInt(pvmTaiID[0]));
                Uutinen ulosMenevaUutinen = new Uutinen(Integer.parseInt(ominaisuudet[0]), uutisenPvm, ominaisuudet[2]);

                return ulosMenevaUutinen;
            } else {
                throw new IllegalArgumentException();
            }
        }

    }

    // Lue sulkusanat tiedostosta

    /**
     * Sulkusanat hakeva metodi
     * @param tiedostonNimi haettavan tiedoston nimi levyllä
     * @return Onnistuiko
     * @throws FileNotFoundException mikäli tiedostoa ei löydy
     */
    public boolean hankiSulkusanat(String tiedostonNimi) throws FileNotFoundException {
        try {
            File tiedosto = new File(tiedostonNimi);
            Scanner tiedLukija = new Scanner(tiedosto);
            while (tiedLukija.hasNextLine()) {
                sulkusanat.add(tiedLukija.nextLine());
            }
            tiedLukija.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Missing file!");
            throw new FileNotFoundException();
        }

    }
}