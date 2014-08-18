/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

/**
 *
 * @author marjolein
 *
 * De class is afgeleid van Exception en heeft 4 constructors, net zoals
 * Exception. Verder zijn er geen methods nodig. Plaats de class in be.vdab.util
 */
public class DatumException extends Exception {

    public DatumException() {
        super();
    }

    public DatumException(String omschrijving) {
        super(omschrijving);
    }

    public DatumException(Throwable ex) {
        super(ex);
    }

    public DatumException(String omschrijving, Throwable ex) {
        super(omschrijving, ex);
    }
}
