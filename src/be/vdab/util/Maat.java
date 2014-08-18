/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author marjolein.vancelst
 *
 * Creëer een enum Maat met waardes: centimeter, decimeter en meter.
 */
public enum Maat implements Serializable/*, Comparable<Maat>*/ {

    //1 meter = 100 centimeter, 1 decimeter = 10 centimeter
    centimeter(1), decimeter(10), meter(100);

    private final int samenZetten;

    private Maat(int samenZetten) {
        this.samenZetten = samenZetten;
    }

    public long getSamenZetten() {
        return (long) samenZetten * samenZetten * samenZetten;
    }
}