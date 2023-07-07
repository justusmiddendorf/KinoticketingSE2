package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kasse.KassenWerkzeug;

/**
 * Mit diesem Werkzeug wird ein Fenster die UI zum bezhalen geöfneet und
 * gesteuert
 * 
 * @author Wir
 */
public class BezahlungsWerkzeug {
    private BezahlungsWerkzeugUI _ui;
    private KassenWerkzeug _kassenWerkzeug;

    /**
     * Initialisiert dieses Werkzeug.
     */
    public BezahlungsWerkzeug() {
        _ui = new BezahlungsWerkzeugUI();
    }

}
