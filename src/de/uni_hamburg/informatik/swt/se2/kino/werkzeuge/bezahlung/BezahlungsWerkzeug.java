package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Mit diesem Werkzeug wird ein Fenster die UI zum bezhalen geöfneet und
 * gesteuert
 * 
 * @author Wir
 */
public class BezahlungsWerkzeug {
    private BezahlungsWerkzeugUI _ui;

    /**
     * Initialisiert dieses Werkzeug.
     */
    public BezahlungsWerkzeug() {
        _ui = new BezahlungsWerkzeugUI();
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
            }

        });
    }

    /**
     * Starten der Barzahlung.
     */
    public void barzahlungstarten() {
        _ui.oeffneDialog();
    }

}
