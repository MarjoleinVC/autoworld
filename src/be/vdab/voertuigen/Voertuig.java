/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author marjolein.vancelst
 *
 * Plaats de class in be.vdab.voertuigen. De class heeft de volgende fields:
 * nummerplaat, class Nummerplaat. Het field nummerplaat wordt bij declaratie
 * onmiddellijk een waarde gegeven en kan later niet meer worden gewijzigd
 * (Systeem van NL, een nummerplaat wordt toegekend aan een voertuig en niet aan
 * de eigenaar).  Merk, een String  DatumEersteIngebruikname, een Datum 
 * Aankoopprijs, een int.  Zitplaatsen, een final int. De class is abstract. De
 * eerste vijf parameters van de constructor van Voertuig zijn: een merk, een
 * datum van eerste ingebruikname en een aankoopprijs, aantal zitplaatsen en een
 * Mens. Er kan eventueel ook een zesde, zevende,… n-tigste parameter zijn. Dit
 * is telkens een Mens. De eerste Mens uit de lijst van de parameters is de
 * bestuurder van het voertuig. De andere (de optionele parameters) zijn
 * inzittenden. Wanneer er teveel inzittenden zouden zijn dan ontstaat de
 * MensException, die in hetzelfde package als Mens zit. Als je de lijst van
 * ingezetenen opvraagt, zit de bestuurder bij in deze lijst. Er kunnen niet
 * meer ingezetenen zijn dan er zitplaatsen zijn in een voertuig. De bestuurder
 * moet een geschikt rijbewijs hebben voor het voertuig. Er moeten inzittenden
 * kunnen uitstappen en bij instappen. Er moet een andere bestuurder kunnen
 * plaats nemen. Een voertuig moet steeds een bestuurder hebben. Elk voertuig
 * heeft minstens één inzittende, anders krijg ontstaat er een
 * IllegalArgumentException. Voorzie de nodige setters en getters. Voorzie een
 * toString, equals en HashCode. De equals maak je op basis van de nummerplaat.
 * Implementeer de interface Comparable op basis van nummerplaat. Zorg ervoor
 * dat het mogelijk is om voertuigen in een OutputStream kunnen bewaard worden.
 * Voorzien een Comparator op basis van merk en eentje op basis van
 * aankoopprijs. Werk de comparators uit als inner classes. Maak voor elke
 * comparator een static getter method om de comparator op te vragen.
 */
public abstract class Voertuig implements Comparable<Voertuig>, Serializable {

    private final Nummerplaat nummerplaat = DIV.getInstance().getNummerplaat();
    private String merk;
    private Datum datumEersteIngebruikname;
    private int aankoopprijs;
    private final int zitplaatsen;
    private Mens bestuurder;
    private final TreeSet<Mens> ingezetenen = new TreeSet<>();

    public Voertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Mens bestuurder, Mens... inzittende) throws MensException {
        validateZitplaatsen(zitplaatsen);
        validateInzittenden(zitplaatsen, inzittende);
        validateNodigRijbewijs(bestuurder);
        toevoegenInzittenden(bestuurder, inzittende);
        this.merk = merk;
        this.datumEersteIngebruikname = datumEersteIngebruikname;
        this.aankoopprijs = aankoopprijs;
        this.zitplaatsen = zitplaatsen;
        this.bestuurder = bestuurder;
        /*ingezetenen.add(bestuurder);
        ingezetenen.addAll(Arrays.asList(inzittende));*/
    }

    private void toevoegenInzittenden(Mens bestuurder, Mens[] inzittende) {
        ingezetenen.add(bestuurder);
        for (Mens inzittende1 : inzittende) {
            if (ingezetenen.contains(inzittende1)) {
            } else {
                ingezetenen.add(inzittende1);
            }
        }
    }

    protected abstract Rijbewijs[] getToegestaneRijbewijzen();

    protected abstract int getMAX_ZITPLAATSEN();

    public Nummerplaat getNummerplaat() {
        return nummerplaat;
    }

    public String getMerk() {
        return merk;
    }

    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikname;
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }

    public String setMerk(String merk) {
        return this.merk = merk;
    }

    public Datum setDatumEersteIngebruikname(Datum datumEersteIngebruikname) {
        return this.datumEersteIngebruikname = datumEersteIngebruikname;
    }

    public int setAankoopprijs(int aankoopprijs) {
        return this.aankoopprijs = aankoopprijs;
    }

    private void validateZitplaatsen(int zitplaatsen) {
        if ((ingezetenen == null)) {
            throw new IllegalArgumentException("Er moet minstens één inzittende zijn.");
        } else if (zitplaatsen > getMAX_ZITPLAATSEN()) {
            throw new IllegalArgumentException("Het maximum toegelaten aantal zitplaatsen is: " + getMAX_ZITPLAATSEN());
        } else if (zitplaatsen <= 0) {
            throw new IllegalArgumentException("Er moet minstens één zitplaats in het voertuig zijn.");
        }
    }

    private void validateInzittenden(int zitplaatsen, Mens[] ingezetenen) throws MensException {
        if (ingezetenen.length > zitplaatsen ) {//-1 omdat er nog plaats moet zijn voor de bestuurder
            throw new MensException("Het aantal beschikbare zitplaatsen voor de inzittenden is: " + zitplaatsen );
        }
    }

    private boolean validateNodigRijbewijs(Mens bestuurder) throws MensException {
        if ((bestuurder.getRijbewijs() != null || (getToegestaneRijbewijzen() != null))) {
            if (!(CollectionUtils.containsAny(Arrays.asList(bestuurder.getRijbewijs()), Arrays.asList(getToegestaneRijbewijzen())))) {
                throw new MensException("Er moet minstens één bestuurder met geldig rijbewijs voor het voertuig zijn.");
            }
            return CollectionUtils.containsAny(Arrays.asList(bestuurder.getRijbewijs()), Arrays.asList(getToegestaneRijbewijzen()));
        }
        throw new MensException("Er moet minstens één bestuurder en één geldig rijbewijs voor het voertuig zijn.");
    }

    public void setBestuurder(Mens nieuweBestuurder) throws MensException {
        Mens vorigeBestuurder = this.bestuurder;
        if ((zitplaatsen > ingezetenen.size()) || isIngezetene(nieuweBestuurder)) {
            if (nieuweBestuurder != null) {
                if (validateNodigRijbewijs(nieuweBestuurder)) {
                    bestuurder = nieuweBestuurder;
                    ingezetenen.add(vorigeBestuurder);
                    ingezetenen.add(bestuurder);
                } else {
                    throw new MensException("De bestuurder " + bestuurder + " moet beschikken voer het juiste rijbewijs.");
                }
            } else {
                throw new NullPointerException("Geef een bestuurder op.");
            }
        } else {
            throw new MensException("Alle zitplaatsen in het voertuig zijn reeds bezet.");
        }
    }

    public void addIngezetene(Mens nieuweInzittende) throws MensException {
        if (nieuweInzittende != null) {
            if (zitplaatsen > ingezetenen.size()) {
                if (!isIngezetene(nieuweInzittende)) {
                    ingezetenen.add(nieuweInzittende);
                } 
            } else {
                throw new MensException("Alle zitplaatsen in het voertuig zijn reeds bezet.");
            }
        } else {
            throw new NullPointerException("Geef een inzittende op.");
        }
    }

    public Set<Mens> getIngezetenen() {
        return Collections.unmodifiableSet(ingezetenen);
    }

    public Set<Mens> getIngezeteneExclusiefBestuurder() {
        TreeSet<Mens> copy = new TreeSet(ingezetenen);
        copy.remove(bestuurder);
        return Collections.unmodifiableSet(copy);
    }

    public boolean isIngezetene(Mens ingezetene) {
        if (ingezetene == null) {
            throw new NullPointerException("Geef een inzittende op.");
        }
        return ingezetenen.contains(ingezetene);
    }

    public void removeIngezetene(Mens inzittende) throws MensException {
        if (ingezetenen.size() > 1) { //er moet minstens 1 inzittende zijn ==> lijst moet na aftrek van inzittende nog minstens 1 element bevatten
            ingezetenen.remove(inzittende);
        } else {
            throw new MensException("Er moet minstens één inzittende zijn.");
        }
    }

    public Mens getBestuurder() throws MensException {
        if (validateNodigRijbewijs(bestuurder) == true) {
            return bestuurder;
        } else {
            throw new MensException("Bestuurder " + bestuurder + " heeft geen geldig rijbewijs.");
        }
    }

    public final int getZitplaatsen() {
        return zitplaatsen;
    }

    @Override
    public boolean equals(Object v) {
        if (!(v instanceof Voertuig)) {
            if (v == null) {
                return false;
            }
            return false;
        }
        return nummerplaat.equals(((Voertuig) v).nummerplaat);
    }

    @Override
    public int hashCode() {
        return getNummerplaat().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %s %s %s", nummerplaat, merk, datumEersteIngebruikname, aankoopprijs, bestuurder));
        if ((getIngezeteneExclusiefBestuurder() != null) && (getIngezeteneExclusiefBestuurder().size() > 0)) {
            sb.append(" [")
                    .append(StringUtils.join(getIngezeteneExclusiefBestuurder(), ", "))
                    .append("]");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Voertuig v) {
        return nummerplaat.compareTo(v.nummerplaat);

    }

    public interface MerkComparator extends Comparator<Voertuig>, Serializable {
    }

    public static MerkComparator getMerkComparator() {
        return new MerkComparator() {
            @Override
            public int compare(Voertuig v1, Voertuig v2) {
                if (v1 != null && v2 != null) {
                    return v1.getMerk().compareTo(v2.getMerk());
                } else {
                    throw new NullPointerException("één van de voertuigen heeft geen waarde");
                }
            }
        };

    }

    public interface AankoopprijsComparator extends Comparator<Voertuig>, Serializable {
    }

    public static AankoopprijsComparator getAankoopprijsComparator() {
        return new AankoopprijsComparator() {
            @Override
            public int compare(Voertuig v1, Voertuig v2) {
                if (v1 != null && v2 != null) {
                    return v1.getAankoopprijs() - (v2.getAankoopprijs());
                } else {
                    throw new NullPointerException("één van de voertuigen heeft geen waarde");
                }
            }
        };
    }
}
