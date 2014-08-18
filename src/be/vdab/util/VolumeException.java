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
 * Plaats de class in be.vdab.util De class is afgeleid van Exception en heeft 4
 * constructors, net zoals Exception. Verder zijn er geen methods nodig.
 */
public class VolumeException extends Exception {

    public VolumeException() {
        super();
    }

    public VolumeException(String omschrijving) {
        super(omschrijving);
    }

    public VolumeException(Throwable ex) {
        super(ex);
    }

    public VolumeException(String omschrijving, Throwable ex) {
        super(omschrijving, ex);
    }
}