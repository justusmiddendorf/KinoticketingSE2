package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Geldbetrag _preis = Geldbetrag.select(0, 0);

    /**
     * Initialisiert dieses Werkzeug.
     */
    public BezahlungsWerkzeug(PlatzVerkaufsWerkzeug platzVerkaufsWerkzeug, Vorstellung vorstellung, Geldbetrag preis) {
        _ui = new BezahlungsWerkzeugUI();
        _platzVerkaufsWerkzeug = platzVerkaufsWerkzeug;
        _vorstellung = vorstellung;
        registriereUIAktionen();
        _ui.getPreisLabel().setText(preis.getFormartiertenBetrag());
        _preis = preis;
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
                if (kostenBeglichen()) {
                    _ui.getModal().setVisible(false);
                    _platzVerkaufsWerkzeug.verkaufePlaetze(_vorstellung);
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
     * Berechnent wie viel Geld zurückgegeben werden muss.
     */
    public boolean kostenBeglichen(){
        String eingegebenerBetrag = _ui.getTextField().getText();
        int centAnteil = (int)(Integer.parseInt(eingegebenerBetrag))/100;
        int euroAnteil = (int)(Integer.parseInt(eingegebenerBetrag))%100;
        Geldbetrag eingabe = Geldbetrag.select(centAnteil,euroAnteil);
        if (Geldbetrag.istGroesserGleich(eingabe, _preis)){
            _ui.getPreisDifferenzLabel().setText("Restbetrag: " + Geldbetrag.berechneDifferenz(eingabe, _preis).getFormartiertenBetrag());
            return true;
        } 
        else {
            _ui.getPreisDifferenzLabel().setText("Der eingegebene Betrag ist nicht groß genug.\n Es fehlen " 
            + Geldbetrag.berechneDifferenz(eingabe, _preis).getFormartiertenBetrag() + "!");
            return false;
        }
    }

}
