/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

/**
 *
 * @author marjolein.vancelst
 *
 * De class zit in het package be.vdab.util.mens Mogelijke waarden: A, B, C, D,
 * BE, CE, DE 
 * Bij het omzetten naar een string wordt BE, CE en De getoond met
 * een + teken tussen de twee letters.
 */
public enum Rijbewijs {

    A, B, BE, C, CE, D, DE;

    @Override
    public String toString() {
        String rijbewijs = super.toString();
        if (rijbewijs.length() == 2) {
            rijbewijs = rijbewijs.charAt(0) + "+" + rijbewijs.charAt(1);
        }
        return rijbewijs;
    }
}
