package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;

/**
 * Mit diesem Werkzeug wird ein Fenster die UI zum bezhalen geöfneet und
 * gesteuert
 * 
 * @author Wir
 */
public class BezahlungsWerkzeug {
    private BezahlungsWerkzeugUI _ui;
    private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
    private Vorstellung _vorstellung;
    private Geldbetrag _zuZahlen;

    /**
     * Initialisiert dieses Werkzeug.
     */
    public BezahlungsWerkzeug(PlatzVerkaufsWerkzeug platzVerkaufsWerkzeug, Vorstellung vorstellung,
            Geldbetrag geldbetrag) {

        _ui = new BezahlungsWerkzeugUI(geldbetrag);

        _platzVerkaufsWerkzeug = platzVerkaufsWerkzeug;
        _vorstellung = vorstellung;
        _zuZahlen = geldbetrag;

        registriereUIAktionen();
    }

    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void registriereUIAktionen() {
        _ui.getCancelButton().addActionListener(e -> _ui.getModal().setVisible(false));

        _ui.getokayButton().addActionListener(e -> {
            _platzVerkaufsWerkzeug.verkaufePlaetze(_vorstellung);
            _ui.getModal().setVisible(false);
        });

        // Hinzufügen eines documentLiseners
        _ui.getTextField().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }

            /**
             * Validiert den Input und kontrolliert, ob es sich um einen gültigen Input
             * handelt.
             */
            private void validateInput() {
                String input = _ui.getTextField().getText();
                Pattern pattern = Pattern.compile("^-?(0|([1-9]\\d{0,6}))(,\\d{2})");

                // Kontrollieren on Input leer ist
                if (input.isEmpty()) {
                    // Error Message setzen und Button aus blenden
                    _ui.getErrorLabel().setText("Bitte gib ein Betrag ein");
                    _ui.getokayButton().setEnabled(false);
                } else {
                    // Vergleichen des Inputs und des Regex
                    Matcher matcher = pattern.matcher(input);
                    if (matcher.matches()) {

                        // Umwandeln des Strings und Restbetrag errechen
                        Geldbetrag gegebenerBetrag = Geldbetrag.stringtoGeldbetrag(input);
                        Geldbetrag summe = berechneRestbetrag(_zuZahlen, gegebenerBetrag);

                        // Kontrolle ob das Geld reicht
                        if (Geldbetrag.istGroesser(_zuZahlen, gegebenerBetrag)) {
                            // Error Message, verbleibender Betrag setzen und Button aus blenden
                            _ui.getErrorLabel().setText("Zu wenig Geld");
                            _ui.getokayButton().setEnabled(false);
                            _ui.getRueckgeldLabel().setText("Es fehlen: " + summe.getFormartiertenBetrag());

                        } else {
                            // Error Message ausblenden, Rückgeld anzeigen und Button aktivieren
                            _ui.getErrorLabel().setText("");
                            _ui.getokayButton().setEnabled(true);
                            _ui.getRueckgeldLabel().setText("Dein Rückgeld beträgt: " + summe.getFormartiertenBetrag());
                        }

                    } else {
                        // Error Message setzen und Button aus blenden
                        _ui.getErrorLabel().setText("Ungültiger Eintrag");
                        _ui.getokayButton().setEnabled(false);
                    }
                }
            }

        });
    }

    /**
     * Starten der Barzahlung.
     */
    public void barzahlungstarten() {
        _ui.oeffneDialog();
    }

    /**
     * Es wird der Betrag des Restgeldes oder noch zuhalneden Geldes berechnet. Es
     * wird immer ein positiver Betrag ausgegeben.
     * Ob es sich um noch zuzahlen oder Rückgeld handelt muss extra geprüft werden.
     * 
     * @param zuZahlend Betrag der zuhalen ist
     * @param kosten    Betrag der gezahlt wird
     * @return Geldbetrag der Differenz
     */
    public Geldbetrag berechneRestbetrag(Geldbetrag zuZahlend, Geldbetrag kosten) {
        Geldbetrag restSumme;
        restSumme = Geldbetrag.berechneDifferenz(zuZahlend, kosten);

        return restSumme;
    }

}
