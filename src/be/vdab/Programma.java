/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import static be.vdab.util.mens.Rijbewijs.*;
import be.vdab.voertuigen.*;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author marjolein.vancelst
 */
public class Programma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws VolumeException, DatumException {

        //Declare "Voertuig"
        SortedSet<Voertuig> voertuigen = new TreeSet<>();
        Personenwagen pw1;
        Personenwagen pw2;
        Pickup pu1;
        Pickup pu2;
        Vrachtwagen vw1;
        Vrachtwagen vw2;

        //Declare "Mens"
        Mens BESTUURDER_A = new Mens("Andree", A);
        Mens BESTUURDER_AB = new Mens("Amadeus", A, B);
        Mens BESTUURDER_B = new Mens("Bernard", B);
        Mens BESTUURDER_BC = new Mens("Beatrice-Clothilde", B, C);
        Mens BESTUURDER_C = new Mens("Catherina", C);
        Mens BESTUURDER_D = new Mens("Didier", D);
        Mens BESTUURDER_BE = new Mens("Beatrice-Emanuella", BE);
        Mens BESTUURDER_BBE = new Mens("Babette-Emanuella", B, BE);
        Mens BESTUURDER_CE = new Mens("Cederic-Eduard", CE);
        Mens BESTUURDER_DE = new Mens("Dominique-Emille", CE);
        Mens BESTUURDER_BBECCE = new Mens("Ammelie", B, BE, C, CE);
        Mens INGEZETENE_A = new Mens("Anita");
        Mens INGEZETENE_B = new Mens("Bert");
        Mens INGEZETENE_C = new Mens("Christina");
        Mens INGEZETENE_D = new Mens("Duts");
        Mens INGEZETENE_E = new Mens("Elsa");
        Mens INGEZETENE_F = new Mens("Fred");
        Mens INGEZETENE_G = new Mens("Gerda");
        Mens INGEZETENE_H = new Mens("Hedwig");
        Mens INGEZETENE_I = new Mens("Ingrid");

        //Declare "Volume"
        Volume VOLUME10 = new Volume(10, 10, 10, Maat.decimeter);
        Volume VOLUME12 = new Volume(12, 12, 12, Maat.decimeter);

        //Declare "Max toegelaten massa"
        int MAXIAAM_TOEGELATEN_MASSA_1 = 7500;
        int MAXIAAM_TOEGELATEN_MASSA_2 = 13500;

        //Declare "Aantal assen"
        int AANTAL_ASSEN_2 = 2;
        int AANTAL_ASSEN_3 = 3;

        //Specifiëren voertuigen
        pw1 = new Personenwagen("Volvo", new Datum(1, 5, 2004), 20000, 5, Color.blue, BESTUURDER_BE, INGEZETENE_B, INGEZETENE_G);
        pw2 = new Personenwagen("Ford", new Datum(1, 5, 2004), 12000, 5, Color.GRAY, BESTUURDER_AB, INGEZETENE_C);
        pu1 = new Pickup("Opel", new Datum(1, 5, 2004), 21000, 5, Color.PINK, VOLUME10, BESTUURDER_B, INGEZETENE_D);
        pu2 = new Pickup("BMW", new Datum(1, 5, 2004), 25000, 5, Color.WHITE, VOLUME10, BESTUURDER_BC, INGEZETENE_E);
        vw1 = new Vrachtwagen("Volvo", new Datum(1, 5, 2004), 45500, 3, VOLUME12, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_BBE, INGEZETENE_A, INGEZETENE_F, INGEZETENE_B);
        vw2 = new Vrachtwagen("Mercedes", new Datum(1, 5, 2004), 40000, 3, VOLUME12, MAXIAAM_TOEGELATEN_MASSA_2, AANTAL_ASSEN_3, BESTUURDER_BBECCE, INGEZETENE_F, INGEZETENE_I);

        //Vullen lijst
        voertuigen.add(pw1);
        voertuigen.add(pw2);
        voertuigen.add(pu1);
        voertuigen.add(pu2);
        voertuigen.add(vw1);
        voertuigen.add(vw2);

        //Sorteren op nummerplaat
        System.out.println();
        System.out.print("*** SortedSet default ***");
        System.out.println();
        for (Voertuig v : voertuigen) {
            System.out.println(v.toString());
        }

        //Sorteren op aankoopprijs
        SortedSet<Voertuig> voertuigenAankoopprijs = new TreeSet<>(new Voertuig.AankoopprijsComparator() {
            @Override
            public int compare(Voertuig v1, Voertuig v2) {
                if (v1 == null || v2 == null) {
                    throw new NullPointerException();
                } else {
                    if (v1.compareTo(v2) == 0) {
                        return 0;
                    }
                    if (v1.getAankoopprijs()-(v2.getAankoopprijs()) < 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });
        voertuigenAankoopprijs.addAll(voertuigen);
        System.out.println();
        System.out.print("*** SortedSet aankoopprijs ***");
        System.out.println();
        for (Voertuig v : voertuigenAankoopprijs) {
            System.out.println(v.toString());
        }

        //Sorteren op merk
        SortedSet<Voertuig> voertuigenMerk = new TreeSet<>(new Voertuig.MerkComparator() {
            @Override
           public int compare(Voertuig v1, Voertuig v2) {
                if (v1 == null || v2 == null) {
                    throw new NullPointerException();
                } else {
                    if (v1.compareTo(v2) == 0) {
                        return 0;
                    }
                    if (v1.getMerk().compareTo(v2.getMerk()) < 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });
        voertuigenMerk.addAll(voertuigen);
        System.out.println();
        System.out.print("*** SortedSet merk ***");
        System.out.println();
        for (Voertuig v : voertuigenMerk) {
            System.out.println(v.toString());
        }

        //Derde set wegschrijven
        FileOutputStream fileVoertuigenMerk = null;
        ObjectOutputStream objVoertuigenMerk = null;
        try {
            fileVoertuigenMerk = new FileOutputStream("wagenpark.ser");
            objVoertuigenMerk = new ObjectOutputStream(fileVoertuigenMerk);
            objVoertuigenMerk.writeObject(voertuigenMerk);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (objVoertuigenMerk != null) {
                try {
                    objVoertuigenMerk.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        //Derde set inlezen in vierde set
        SortedSet<Voertuig> voertuigenVier = new TreeSet<>();
        FileInputStream fileVoertuigenVier = null;
        ObjectInputStream objVoertuigenVier = null;
        try {
            fileVoertuigenVier = new FileInputStream("wagenpark.ser");
            objVoertuigenVier = new ObjectInputStream(fileVoertuigenVier);
            voertuigenVier = (SortedSet<Voertuig>) objVoertuigenVier.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (objVoertuigenVier != null) {
                try {
                    objVoertuigenVier.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        //Sorteren op merk, inlezen van wagenpark.ser
        System.out.println();
        System.out.print("*** SortedSet merk ingelezen van wagenpark.ser ***");
        System.out.println();
        for (Voertuig v : voertuigenVier) {
            System.out.println(v.toString());
        }
    }
}
