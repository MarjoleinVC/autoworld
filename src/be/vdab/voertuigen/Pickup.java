/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author marjolein.vancelst
 */
public class Pickup extends Personenwagen implements Comparable<Voertuig>, Serializable, Laadbaar {

    private Color kleur;
    private static final int MAX_ZITPLAATSEN = 5;
    private Volume laadvolume;

    public Pickup(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Volume laadvolume, Mens bestuurder) throws MensException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, kleur, bestuurder);
        this.laadvolume = laadvolume;
    }

    public Pickup(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Volume laadvolume, Mens bestuurder, Mens... ingezetenen) throws MensException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, kleur, bestuurder, ingezetenen);
        this.laadvolume = laadvolume;
    }
    
    @Override
    protected int getMAX_ZITPLAATSEN(){
         return MAX_ZITPLAATSEN;
     }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        this.laadvolume = laadvolume;
    }

    @Override
    public Volume getLaadvolume() {        
        return laadvolume;
    }

    @Override
    public String toString() {
        return String.format("%s %s",super.toString(),getLaadvolume());
    }
}
