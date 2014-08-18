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
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;

/**
 *
 * @author marjolein.vancelst
 */
public class Vrachtwagen extends Voertuig implements Comparable<Voertuig>, Serializable, Laadbaar {

    private static final int MAX_ZITPLAATSEN = 3;
    private Volume laadvolume;
    private int maximaalToegelatenMassa;
    private int aantalAssen;

    public Vrachtwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Volume laadvolume, int maximaalToegelatenMassa, int aantalAssen, Mens bestuurder) throws MensException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder);
        this.laadvolume = laadvolume;
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        this.aantalAssen = aantalAssen;
    }

    public Vrachtwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Volume laadvolume, int maximaalToegelatenMassa, int aantalAssen, Mens bestuurder, Mens... inzittende) throws MensException {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder);
        this.laadvolume = laadvolume;
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        this.aantalAssen = aantalAssen;
    }

    @Override
    protected int getMAX_ZITPLAATSEN() { /// zelf toegevoegd
        return MAX_ZITPLAATSEN;
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }
    
    @Override
    public Volume getLaadvolume(){
        return laadvolume;
    }
    
    public int getMaximaalToegelatenMassa(){
        return maximaalToegelatenMassa;
    }
    
    public int getAantalAssen(){
        return aantalAssen;
    }
    
    @Override
    public void setLaadvolume(Volume laadvolume){
        this.laadvolume = laadvolume;
    }
    
    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa){
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
    }
    
    public void setAantalAssen(int aantalAssen){
        this.aantalAssen = aantalAssen;
    }
    
    //String.format("%s %s %s %s %s assen:%s, maximaal toegelaten massa:%s, laadvolume:%s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)", AA1_STRING, MTM1_STRING, VOLUME10_STRING)
     @Override
    public String toString() {
        return String.format("%s assen:%s, maximaal toegelaten massa:%s, laadvolume:%s",super.toString(), aantalAssen, maximaalToegelatenMassa, laadvolume);
    }
}
