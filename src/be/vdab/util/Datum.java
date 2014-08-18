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
 * Maak een immutable class Datum. Plaats de class in be.vdab.util De class
 * heeft 3 integer fields: dag, maand, jaar. Zorg ervoor deze fields slechts één
 * maal een waarde kunnen krijgen (onmiddellijk bij de declaratie of in de
 * constructor). De class aanvaardt alleen geldige datums tussen 01/01/1583 en
 * 31/12/4099. Een poging om een foute datum te creëren leidt tot een
 * DatumExcepetion. De constructor aanvaart 3 integers (dag, maand, jaar).
 * Voorzie de class van de nodige getters. Voorzie een toString (in het formaat
 * dd/mm/jjjj) , een equals en een hashCode. Zorg ervoor dat datums in een
 * OutputStream kunnen bewaard worden. Implementeer de interface Comparable
 */
public final class Datum implements Comparable<Datum>, Serializable {

    private final int dag;
    private final int maand;
    private final int jaar;

    private static int[] dagenPerMaand = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Datum(int dag, int maand, int jaar) throws DatumException {
        validate(dag, maand, jaar);
        this.dag = dag;
        this.maand = maand;
        this.jaar = jaar;
    }

    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }

    private boolean isSchrikkeljaar(int jaar) {
        if ((jaar % 100 == 0) && (jaar % 400 == 0)) {
            return true;
        } else {
            return (jaar % 100 != 0) && (jaar % 4 == 0);
        }
    }

    private void validate(int dag, int maand, int jaar) throws DatumException {
        if (!(jaar >= 1583 && jaar <= 4099
                && maand >= 1 && maand <= 12
                && dag >= 1 && dag <= dagenPerMaand[maand - 1] + ((maand == 2 && isSchrikkeljaar(jaar)) ? 1 : 0))) {
            throw new DatumException("Geef een geldige datum in.");
        }
    }

    @Override
    public boolean equals(Object d) {
        if (!(d instanceof Datum)) {
            return false;
        }
        final Datum andere = (Datum) d;
        return (this.dag == andere.dag) && (this.maand == andere.maand) && (this.jaar == andere.jaar);
    }

    private int getUniekeCode() {
        return jaar * 10000 + maand * 100 + dag;
    }

    @Override
    public int hashCode() {
        return getUniekeCode();
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dag, maand, jaar);
    }

    //assertion failed error
    @Override
    public int compareTo(Datum d) {
        return getUniekeCode() - d.getUniekeCode();
    }
}
