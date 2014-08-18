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
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author marjolein
 */
public class Programma {

    /**
     * @param args the command line arguments
     * @throws be.vdab.util.DatumException
     * @throws be.vdab.util.VolumeException
     */
    public static void main(String[] args) throws DatumException, VolumeException {
        Set voertuigen = new TreeSet<>();
        fillSet(voertuigen);
        System.out.println();
        System.out.print("*** SortedSet default ***");
        for (Object obj : voertuigen) {
            System.out.println(obj);

        }

        Set voertuigenAankoopprijs = new TreeSet<>(Voertuig.getAankoopprijsComparator());
        voertuigenAankoopprijs.addAll(voertuigen);
        System.out.println();
        System.out.print("*** SortedSet aankoopprijs ***");
        for (Object obj : voertuigenAankoopprijs) {
            System.out.println(obj);

        }

        Set voertuigenMerk = new TreeSet<>(Voertuig.getAankoopprijsComparator());
        voertuigenMerk.addAll(voertuigen);
        System.out.println();
        System.out.print("*** SortedSet merk ***");
        for (Object obj : voertuigenMerk) {
            System.out.println(obj);

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
                    //outputstream sluiten
                    objVoertuigenMerk.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        //Derde set inlezen in vierde set
        Set voertuigenVier = new TreeSet<>();
        FileInputStream fileVoertuigenVier = null;
        ObjectInputStream objVoertuigenVier = null;
        try {
            fileVoertuigenVier = new FileInputStream("wagenpark.ser");
            objVoertuigenVier = new ObjectInputStream(fileVoertuigenVier);
            voertuigenVier = (SortedSet<Voertuig>) objVoertuigenVier.readObject();
            showSet(voertuigen);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            //de file sluiten
            if (objVoertuigenVier != null) {
                try {
                    objVoertuigenVier.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        System.out.println();
        System.out.print("*** SortedSet merk ingelezen van wagenpark.ser ***");
        for (Object obj : voertuigenVier) {
            System.out.println(obj);
        }
    }

    private static void fillSet(Set voertuigen) throws DatumException, VolumeException {
        try {
            Mens BESTUURDER_A = new Mens("Andree", A);
            Mens BESTUURDER_AB = new Mens("Amadeus", A, B);
            Mens BESTUURDER_B = new Mens("Bernard", B);
            Mens BESTUURDER_BC = new Mens("Beatrice-Clothilde", B, C);
            Mens BESTUURDER_C = new Mens("Catherina", C);
            Mens BESTUURDER_D = new Mens("Didier", D);
            Mens INGEZETENE_A = new Mens("Anita");
            Mens INGEZETENE_B = new Mens("Bert");
            Mens INGEZETENE_C = new Mens("Christina");
            Mens INGEZETENE_D = new Mens("Duts");
            Mens INGEZETENE_E = new Mens("Elsa");
            Mens INGEZETENE_F = new Mens("Fred");

            Volume VOLUME10 = new Volume(10, 10, 10, Maat.decimeter);
            Volume VOLUME12 = new Volume(12, 12, 12, Maat.decimeter);

            int MAXIAAM_TOEGELATEN_MASSA_1 = 7500;
            int MAXIAAM_TOEGELATEN_MASSA_2 = 13500;

            int AANTAL_ASSEN_2 = 2;
            int AANTAL_ASSEN_3 = 3;

            try {
                voertuigen.add(new Personenwagen("Volvo", new Datum(1, 5, 2004), 20000, 5, Color.blue, BESTUURDER_A, INGEZETENE_B));
                voertuigen.add(new Personenwagen("Ford", new Datum(1, 5, 2004), 20000, 5, Color.GRAY, BESTUURDER_AB, INGEZETENE_C));
                voertuigen.add(new Pickup("Opel", new Datum(1, 5, 2004), 20000, 5, Color.PINK, VOLUME10, BESTUURDER_B, INGEZETENE_D));
                voertuigen.add(new Pickup("BMW", new Datum(1, 5, 2004), 20000, 5, Color.WHITE, VOLUME10, BESTUURDER_BC, INGEZETENE_E));
                voertuigen.add(new Vrachtwagen("Volvo", new Datum(1, 5, 2004), 20000, 5, VOLUME12, MAXIAAM_TOEGELATEN_MASSA_1, AANTAL_ASSEN_2, BESTUURDER_C, INGEZETENE_A));
                voertuigen.add(new Vrachtwagen("Mercedes", new Datum(1, 5, 2004), 20000, 5, VOLUME12, MAXIAAM_TOEGELATEN_MASSA_2, AANTAL_ASSEN_3, BESTUURDER_D, INGEZETENE_F));
            } catch (DatumException ex) {
                throw new DatumException(ex);
            }
        } catch (VolumeException ex) {
            throw new VolumeException(ex);
        }
    }
