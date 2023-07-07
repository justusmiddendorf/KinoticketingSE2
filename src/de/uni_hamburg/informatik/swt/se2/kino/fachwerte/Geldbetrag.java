package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;
import java.util.Map;

/**
 * Der neue Geldbetrag welchen wir erweitern möchten.
 * 
 * @author Furkan
 * @version 17.06.2023
 */
public final class Geldbetrag {
    /**
     * Der Euro Anteil des Geldbetrages (die Stellen vor dem Komma).
     */
    private final int _euroAnteil;

    /**
     * Der Cent Anteil des Geldbetrages (die Stellen nach dem Komma).
     */
    private final int _centAnteil;

    private static Map<String, Geldbetrag> _werteMenge = new HashMap<>();

    /**
     * Erzeugt einen neuen Geldbetrag.
     * 
     * @param eurocent Eurocent kombinieren Euro und Cent in einem Wert ohne Komma
     */
    Geldbetrag(int eurocent) {
        _euroAnteil = eurocent / 100;
        _centAnteil = eurocent % 100;
    }

    /**
     * Erzeugt einen neuen Geldbetrag.
     * 
     * @param euroAnteil Euro Teil des Geldbetrages
     * @param centAnteil Cent Teil des Geldbetrages
     */
    private Geldbetrag(int euroAnteil, int centAnteil) {
        _euroAnteil = euroAnteil;
        _centAnteil = centAnteil;
    }

    public static Geldbetrag select(int euroAnteil, int centAnteil) {
        assert istGueltigerEuroAnteil(
                euroAnteil) : "Vorbedingung verletzt: istGueltigerEuroAnteil(euroAnteil)";
        assert istGueltigerCentAnteil(
                centAnteil) : "Vorbedingung verletzt: istGueltigerCentAnteil(centAnteil)";

        String key = euroAnteil + "," + centAnteil;

        if (!_werteMenge.containsKey(key))
            _werteMenge.put(key, new Geldbetrag(euroAnteil, centAnteil));

        return _werteMenge.get(key);
    }

    /**
     * Prüft ob der gegebene Euro Anteil so akzeptiert werden kann.
     * 
     * @param euroAnteil Der Euro Anteil als ganze Zahl
     * 
     * @return True wenn wir keine negativen Euro haben.
     */
    public static boolean istGueltigerEuroAnteil(int euroAnteil) {
        return euroAnteil >= 0;
    }

    /**
     * Prüft ob der gegebene Cent Anteil so akzeptiert werden kann.
     * 
     * @param centAnteil Der Cent Anteil als ganze Zahl
     * 
     * @return True wenn die Cent so akzeptiert werden können.
     */
    public static boolean istGueltigerCentAnteil(int centAnteil) {
        return centAnteil >= 0 && centAnteil < 100;
    }

    /**
     * Addiert zwei Geldbeträge miteinander.
     * 
     * @param summand1 Der erste Geldbetrag
     * @param summand2 Der zweite Geldbetrag
     * @return Die Summe der Geldbeträge
     * 
     * @require summand1 != null
     * @require summand2 != null
     * 
     * @ensure result != null
     */
    public static Geldbetrag addiere(Geldbetrag summand1, Geldbetrag summand2) {
        assert summand1 != null : "Vorbedingung verletzt: summand1 != null ";
        assert summand2 != null : "Vorbedingung verletzt: summand2 != null ";

        int summe = summand1.getCentBetrag() + summand2.getCentBetrag();

        return select(summe / 100, summe % 100);
    }

    /**
     * Addiert einen Geldbetrag zu unserem hinzu.
     * 
     * @param summand Geldbetrag der hinzu gerechnet wird
     * @return Neuer GeldBetrag mit der Summe der Geldbeträge
     * 
     * @require summand != null
     * 
     * @ensure result != null
     */
    public Geldbetrag addiere(Geldbetrag summand) {
        assert summand != null : "Vorbedingung verletzt: summand != null ";

        int summe = getCentBetrag() + summand.getCentBetrag();

        return select(summe / 100, summe % 100);
    }

    /**
     * Die Subtraktion auf dem Geldbetrag.
     * 
     * @param wert1 der Wert von dem Abgezogen wird
     * @param wert2 der Wert der Abgezogen wird
     * @return Das Ergebnisse
     * @throws IllegalArgumentException wenn der erste Wert kleiner ist als der
     *                                  Zweite
     */
    public static Geldbetrag subtrahiere(Geldbetrag wert1, Geldbetrag wert2) {

        if (istGroesserGleich(wert1, wert2)) {
            int euroAnteil = wert1.getEuroAnteil() - wert2.getEuroAnteil();
            int centAnteil = wert1.getCentAnteil() - wert2.getCentAnteil();

            if (centAnteil < 0) {
                euroAnteil--;
                centAnteil += 100;
            }

            return select(euroAnteil, centAnteil);
        }
        throw new IllegalArgumentException("Der zweite Wert kann nciht vom ersten abgezogen werden, da er größer ist.");
    }

    /**
     * Berechnet die Differenz aus zwei Geldbeträgen.
     * 
     * @param betrag1 Der erste Geldbetrag
     * @param betrag2 Der zweite Geldbetrag
     * 
     * @return Die Differenz als neuer Geldbetrag
     */
    public static Geldbetrag berechneDifferenz(Geldbetrag betrag1,
            Geldbetrag betrag2) {
        if (istGroesserGleich(betrag1, betrag2)) {
            int euroAnteil = betrag1.getEuroAnteil() - betrag2.getEuroAnteil();
            int centAnteil = betrag1.getCentAnteil() - betrag2.getCentAnteil();

            if (centAnteil < 0) {
                euroAnteil--;
                centAnteil += 100;
            }

            return select(euroAnteil, centAnteil);
        }

        return berechneDifferenz(betrag2, betrag1);
    }

    /**
     * Gleicht zwei Geldbeträge und prüft ob der erste Geldbetrag größer ist als der
     * zweite.
     * 
     * @param betrag1 Der erste Geldbetrag
     * @param betrag2 Der zweite Geldbetrag
     * 
     * @return True wenn der erste Geldbetrag größer ist.
     */
    public static boolean istGroesserGleich(Geldbetrag betrag1,
            Geldbetrag betrag2) {
        int euro1 = betrag1.getEuroAnteil();
        int euro2 = betrag2.getEuroAnteil();
        int cent1 = betrag1.getCentAnteil();
        int cent2 = betrag2.getCentAnteil();

        return euro1 > euro2 || euro1 == euro2 && cent1 >= cent2;
    }

    /**
     * Gleicht zwei Geldbeträge und prüft ob der erste Geldbetrag größer ist als der
     * zweite.
     * 
     * @param betrag1 Der erste Geldbetrag
     * @param betrag2 Der zweite Geldbetrag
     * 
     * @return True wenn der erste Geldbetrag größer ist.
     */
    public static boolean istGroesser(Geldbetrag betrag1,
            Geldbetrag betrag2) {
        int euro1 = betrag1.getEuroAnteil();
        int euro2 = betrag2.getEuroAnteil();
        int cent1 = betrag1.getCentAnteil();
        int cent2 = betrag2.getCentAnteil();

        return euro1 > euro2 || euro1 == euro2 && cent1 > cent2;
    }

    /**
     * Multiplizert den aktuellen Geldbetrag um einem Faktor.
     * 
     * @param faktor Der Faktor um den multiplziert wird
     * @return Neuer Geldbetrag um den Faktor multiplziert
     * 
     * @require faktor >= 0
     * 
     * @ensure result != null
     */
    public Geldbetrag multipliziere(int faktor) {
        assert faktor >= 0 : "Vorbedingung verletzt: faktor >= 0 ";

        int produkt = getCentBetrag() * faktor;

        return select(produkt / 100, produkt % 100);
    }

    /**
     * Bildet einen String aus dem Euro / Cent Anteil.
     * 
     * @return Der Geldbetrag mit einem Komma getrennt und als String
     */
    public String getFormartiertenBetrag() {
        return _euroAnteil + "," + getFormatiertenCentAnteil();
    }

    /**
     * HILFSMETHODE
     * Bildet einen String aus dem aktuellen Cent Anteil.
     * 
     * @return Der String des Cent Betrags
     */
    private String getFormatiertenCentAnteil() {
        if (_centAnteil < 10) {
            return "0" + _centAnteil;
        }

        return "" + _centAnteil;
    }

    /**
     * Gibt den Euro Anteil des Geldbetrages wieder.
     * 
     * @return Der Euro Anteil
     */
    public int getEuroAnteil() {
        return _euroAnteil;
    }

    /**
     * Gibt den Cent Anteil des Geldbetrages wieder.
     * 
     * @return Der Cent Anteil
     */
    public int getCentAnteil() {
        return _centAnteil;
    }

    /**
     * Gibt den Geldbetrag als ganze Zahl wieder.
     * 
     * @return Der Betrag
     */
    public int getCentBetrag() {
        return _euroAnteil * 100 + _centAnteil;
    }

    /**
     * Wandelt einen String in der Richtigen Form in einen Geldbetrag
     * 
     * @param input Umzuwandelnder String
     * @return String als Geldbetrag
     */
    public static Geldbetrag stringtoGeldbetrag(String input) {

        String[] parts = input.split(",");

        int firstNumber = Integer.parseInt(parts[0]);
        int secondNumber = Integer.parseInt(parts[1]);

        Geldbetrag neuerBetrag = select(firstNumber, secondNumber);
        return neuerBetrag;
    }
}