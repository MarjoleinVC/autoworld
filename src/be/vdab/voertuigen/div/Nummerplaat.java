/*
* @Autor Frank
* Dit is een oefening.
 */

package be.vdab.voertuigen.div;

import java.io.Serializable;

/**
 *
 * @author frank.roelants
 *
 * Maak een immutable class nummerplaat (immutable  final fields). Plaats de
 * class in een package be.vdab.voertuigen.div Er is één constructor en die
 * aanvaardt een String plaat en heeft default visibility. Voorzie een
 * getPlaat(). Voorzie een toString, een equals en een hashCode. Zorg ervoor dat
 * nummerplaten in een OutputStream kunnen bewaard worden. Implementeer de
 * interface Comparable.
 */
public class Nummerplaat implements Comparable<Nummerplaat>, Serializable {

    public final String plaat;

    Nummerplaat(String plaat) {
        this.plaat = plaat;
    }

    public String getPlaat() {
        return plaat;
    }

    @Override
    public boolean equals(Object n) {
        if (!(n instanceof Nummerplaat)) {
            return false;
        }
        Nummerplaat andere = (Nummerplaat) n;
        return this.plaat.equals(andere.plaat);
    }

    @Override
    public int hashCode() {
        return plaat.hashCode();
    }

    @Override
    public String toString() {
        return plaat;
    }

    @Override
    public int compareTo(Nummerplaat object) {
        return this.getPlaat().compareTo(object.getPlaat());
    }
}
