/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

/**
 *
 * @author marjolein.vancelst
 *
 * Deze interface zit in de package be.vdab.util De interface definieert een
 * getter en een setter voor laadvolume.
 */
public interface Laadbaar {

    public void setLaadvolume(Volume laadvolume); 

    public Volume getLaadvolume();
}