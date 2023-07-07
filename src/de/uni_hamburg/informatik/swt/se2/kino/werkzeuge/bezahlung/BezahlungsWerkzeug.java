package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /**
     * Initialisiert dieses Werkzeug.
     */
    public BezahlungsWerkzeug(PlatzVerkaufsWerkzeug platzVerkaufsWerkzeug, Vorstellung vorstellung,
            Geldbetrag geldbetrag) {
        System.out.println("TEST " + geldbetrag.getFormartiertenBetrag());
        _ui = new BezahlungsWerkzeugUI(geldbetrag);
        _platzVerkaufsWerkzeug = platzVerkaufsWerkzeug;
        _vorstellung = vorstellung;
        registriereUIAktionen();
    }

    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void registriereUIAktionen() {
        _ui.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ui.getModal().setVisible(false);
            }
        });

        _ui.getokayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ui.getModal().setVisible(false);
                _platzVerkaufsWerkzeug.verkaufePlaetze(_vorstellung);
            }

        });

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
             * Validiert den Input un kontrolliert, ob es sich um einen gültigen Input
             * handelt.
             */
            private void validateInput() {
                String input = _ui.getTextField().getText();
                Pattern pattern = Pattern.compile("^-?(0|([1-9]\\d{0,6}))(,\\d{2})?");

                if (input.isEmpty()) {
                    _ui.getErrorLabel().setText("Bitte gib ein Betrag ein");
                    _ui.getokayButton().setEnabled(false);
                } else {
                    Matcher matcher = pattern.matcher(input);
                    if (matcher.matches()) {
                        _ui.getErrorLabel().setText("");
                        _ui.getokayButton().setEnabled(true);
                    } else {
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
    public void barzahlungstarten(Geldbetrag gesammtSumme) {
        _ui.oeffneDialog();
    }

    public Geldbetrag berechneRestbetrag(Geldbetrag zuZahlend, Geldbetrag kosten) {
        return Geldbetrag.select(0, 0);
    }
}
