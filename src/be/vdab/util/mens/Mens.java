/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author marjolein.vancelst
 *
 * De class zit in het package be.vdab.util.mens De class heeft de volgende
 * fields: 
 *      - de string naam. 
 *      - een verzameling rijbewijzen.
 */
public class Mens implements Comparable<Mens>, Serializable {

    private final String naam;
    public final TreeSet<Rijbewijs> rijbewijzen = new TreeSet<>(); //TreeSet om reeks objecten gesorteerd op te slaan + geen dubbels

    public Mens(String naam, Rijbewijs... rijbewijs) { //"..." wijst op een variabele set, niet gekend hoeveel rijbewijzen 1 "Mens" heeft
        this.naam = naam;
        rijbewijzen.addAll(Arrays.asList(rijbewijs));
    }

    public String getNaam() {
        return this.naam;
    }

    public Rijbewijs[] getRijbewijs() {
        return (Rijbewijs[]) rijbewijzen.toArray(new Rijbewijs[0]);
    }

    @Override
    public boolean equals(Object m) {
        if (!(m instanceof Mens)) {
            return false;
        }
        final Mens andere = (Mens) m;
        return naam.equals(andere.naam) && rijbewijzen.equals(andere.rijbewijzen);
    }

    @Override
    public int hashCode() {
        return getStringRepresentation().hashCode();
    }

    @Override
    public String toString() {
        return getStringRepresentation();
    }

    private String getStringRepresentation() {
        /*
         zonder Anita
         met Ammelie(B, B+E, C, C+E)
         */
        StringBuilder sb = new StringBuilder(naam);
        if (!rijbewijzen.isEmpty()) {
            sb.append("(").
                    append(StringUtils.join(rijbewijzen, ", ")).
                    append(")");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Mens m) {
        return naam.compareTo(m.getNaam());
    }
}
