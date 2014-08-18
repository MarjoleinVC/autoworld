/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;

/**
 *
 * @author marjolein.vancelst
 *
 * De class Boekentas zit in het package be.vdab.schoolgerief en implementeert
 * Laadbaar. De class heeft twee fields, laadvolume en kleur (een String).
 * Voorzien een constructor met parameters om de fields te initialiseren.
 * Voorzie de nodige getters en setters en override de nodige methods. Zorg
 * ervoor dat Boekentassen in een OutputStream kunnen bewaard worden. Voorzie
 * een toString, equals en HashCode. De equals maak je op basis van de
 * laadvolume en kleur. Laadvolume en kleur moeten ingevuld worden, zoniet wordt
 * een IllegalArgumentException gethrowd.
 */
public class Boekentas implements Serializable, Laadbaar {

    private Volume laadvolume;
    private String kleur;

    public Boekentas(String kleur, Volume laadvolume) {
        validate(kleur, laadvolume);
        this.laadvolume = laadvolume;
        this.kleur = kleur;
    }

    private void validate(String kleur, Volume laadvolume) {
        if (laadvolume == null || kleur == null) {
            throw new IllegalArgumentException("Geef een laadvolume en kleur op.");
        }
    }

    public void setKleur(String kleur) {
        if (kleur != null) {
            this.kleur = kleur;
        } else {
            throw new IllegalArgumentException("Geef een kleur op.");
        }
    }

    public String getKleur() {
        return kleur;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        if (laadvolume != null) {
            this.laadvolume = laadvolume;
        } else {
            throw new IllegalArgumentException("Geef een laadvolume op.");
        }
    }

    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }

    @Override
    public boolean equals(Object b) {
        if (!(b instanceof Boekentas)) {
            if (b == null) {
                return false;
            }
            return false;
        }
        final Boekentas andere = (Boekentas) b;
        return this.kleur.equals(andere.kleur) && this.laadvolume.equals(andere.laadvolume);
    }

    @Override
    public int hashCode() {
        return kleur.hashCode() * laadvolume.toString().hashCode();
    }

    @Override
    public String toString() {
        return String.format("boekentas %s %s", getKleur(), getLaadvolume());
    }
}
