/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen.div;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author marjolein.vancelst
 *
 * Maak een class DIV. Plaats de class in een package be.vdab.voertuigen.div.
 * Deze class is een singleton, gebruik hiervoor volgende code: 
 *      private static final DIV instance = new DIV(); 
 * Maak een method getInstance() die de waarde
 * van de instance terug geeft. Maak een method getNummerplaat, die nummerplaat
 * objecten terug geeft. Om de complexiteit rond de nummerplaat te beperken
 * spreken we af dat: 
 *      - een nummerplaat start met AAA gevolgd door 3 cijfers. Je
 *          start met 001. 
 *      - telkens een nieuwe nummerplaat gevraagd wordt, verhoogd de
 *          nummer. 
 *      - eenmaal aan 999 gekomen mag terug verder gegaan worden met 001.
 * Zorg ervoor dat DIV nummerplaatobjecten kan maken, maar andere classes
 * (buiten het package be.vdab.voertuigen.div) niet.
 */
public class DIV {

    private static final DIV instance = new DIV();

    public DIV() {
    }

    public static DIV getInstance() {
        return instance;
    }

    private static int nummer = 0;

    public Nummerplaat getNummerplaat() {
        if (nummer < 999) {
            nummer++;
        } else {
            nummer = 1;
        }
        return new Nummerplaat(String.format("AAA%03d", nummer));
    }
}