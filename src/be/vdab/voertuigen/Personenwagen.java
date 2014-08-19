/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author marjolein.vancelst
 */
public class Personenwagen extends Voertuig implements Comparable<Voertuig>, Serializable {

    private Color kleur;
    private static final int MAX_ZITPLAATSEN = 8;

    public Personenwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Mens bestuurder) throws MensException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder);
        /*if (zitplaatsen > MAX_ZITPLAATSEN) {
            throw new IllegalArgumentException("Het maximum toegelaten aantal zitplaatsen is: " + MAX_ZITPLAATSEN);
        }*/
        this.kleur = kleur;
    }

    public Personenwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Mens bestuurder, Mens... ingezetenen) throws MensException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, ingezetenen);
        /*if (zitplaatsen > MAX_ZITPLAATSEN) {
            throw new IllegalArgumentException("Het maximum toegelaten aantal zitplaatsen is: " + MAX_ZITPLAATSEN);
        }*/
        this.kleur = kleur;
    }

    @Override
    protected int getMAX_ZITPLAATSEN() { /// zelf toegevoegd
        return MAX_ZITPLAATSEN;
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }

    public Color setKleur(Color kleur) {
        return this.kleur = kleur;
    }

    public Color getKleur() {
        return kleur;
    }

    @Override
    public String toString() {
        return String.format("%s %s",super.toString(),getZitplaatsen());
    }
}
