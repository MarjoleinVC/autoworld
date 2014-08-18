/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author marjolein.vancelst
 *
 * Plaats de class in be.vdab.util De class volume is immutable, de 4 fields
 * kunnen slechts één maal een waarde krijgen (onmiddellijk bij de declaratie of
 * in de constructor). Een volume heeft 4 fields zijn hoogte, breedte, diepte en
 * maat Maat is een Maat object, de andere field zijn int’s. Een volume heeft
 * een constructor met 4 parameters. Voorzien een method getVolume die het
 * volume berekent, het resultaat is een long. De equals maak je op basis van de
 * 4 fields. Implementeer de interface Comparable op basis van volume. Zorg
 * ervoor dat volumes in een OutputStream kunnen bewaard worden. Negatieve
 * volume’s kunnen niet en leiden tot een VolumeException.
 */
public class Volume implements Comparable<Volume>, Serializable {

    private final int hoogte;
    private final int breedte;
    private final int diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        if (maat != null) {
            if ((hoogte < 0) && (breedte < 0) && (diepte < 0)) {
                throw new VolumeException("Het getal moet positief zijn.");
            } else {
                this.hoogte = hoogte;
                this.breedte = breedte;
                this.diepte = diepte;
                this.maat = maat;
            }
        } else {
            throw new NullPointerException("De waarde van de maat is null.");
        }
    }

    public int getHoogte() {
        return hoogte;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getDiepte() {
        return diepte;
    }

    public Maat getMaat() {
        return maat;
    }

    public long getVolume() {
        long volume = getHoogte() * getBreedte() * getDiepte() * maat.getSamenZetten();
        return volume;
    }

    @Override
    public boolean equals(Object v) {
        if (v != null) {
            final Volume andere = (Volume) v;
            return (this.hoogte == andere.hoogte) && (this.breedte == andere.breedte) && (this.diepte == andere.diepte) && (this.maat == andere.maat);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        Long Volume = getVolume();
        return Volume.hashCode();
    }

    @Override
    public String toString() {
        return getHoogte() + "(h)x" + getBreedte() + "(b)x" + getDiepte() + "(d) " + getMaat();
    }

    @Override
    public int compareTo(Volume v) {
        if (v != null) {
            return new Long(getVolume()).compareTo(v.getVolume());
        } else {
            throw new NullPointerException("De waarde van de parameter is null.");
        }
    }
}
