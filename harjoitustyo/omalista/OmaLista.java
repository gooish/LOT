package harjoitustyo.omalista;

import java.util.LinkedList;

import harjoitustyo.apulaiset.Ooperoiva;

//OmaLista -luokka
/** Konkreettinen OmaLista-luokka, joka laajentaa javan LinkedList
 * -tietotyyppiä lisäten siihen järjestävän alkionlisäystoiminnon.
 * <p>
 * Harjoitustyö, OOPE2 -kurssi.
 * <p>
 * @author Elli Mansikka (iivari.mansikka@tuni.fi)
 * Tampereen yliopisto
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    @Override
    public void lisää(E uusi) throws IllegalArgumentException {
        if (uusi instanceof Comparable && uusi != null) {
            boolean onnistuuko = false;
            if (this.size() < 1) {
                this.add(uusi);
                onnistuuko = true;
            } else {
                for (int i = 0; i < this.size(); i++) {
                    if (toComparable(uusi).compareTo(this.get(i)) < 0 ) {
                        this.add(i, uusi);
                        onnistuuko = true;
                        break;
                    }
                }
            }
            if (onnistuuko == false) {
                this.add(uusi);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private Comparable toComparable(E e) throws IllegalArgumentException {
        if (e instanceof Comparable) {
            Comparable ulos = (Comparable) e;
            return ulos;
        }
        else {
            throw new IllegalArgumentException();
        }
        
    }
}