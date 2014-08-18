/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

/**
 *
 * @author marjolein.vancelst
 */
public class MensException extends RuntimeException {

    public MensException() {
        super();
    }

    public MensException(String omschrijving) {
        super(omschrijving);
    }

    public MensException(Throwable ex) {
        super(ex);
    }

    public MensException(String omschrijving, Throwable ex) {
        super(omschrijving, ex);
    }
}
