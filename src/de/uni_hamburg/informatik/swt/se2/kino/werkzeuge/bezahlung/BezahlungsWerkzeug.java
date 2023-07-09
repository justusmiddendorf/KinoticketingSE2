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

        //if (!kostenBeglichen()){ _ui.getokayButton().setEnabled(false)
        _ui.getokayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kostenBeglichen()) {
                    _ui.getModal().setVisible(false);
                    _platzVerkaufsWerkzeug.verkaufePlaetze(_vorstellung);
                }
            }
        });
        //}
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
        //assert gueltigeEingabe(_ui.getTextField().getText()) : "Vorbedingung wurde verletzt: gueltigeEingabe(_ui.getTextField().getText())";
        String eingegebenerBetrag = _ui.getTextField().getText();
        if (gueltigeEingabe(eingegebenerBetrag)) {
            Geldbetrag eingabe = eingabeZuGeldbetrag(eingegebenerBetrag);
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
        else {
            _ui.getPreisDifferenzLabel().setText("Restbetrag: - Bitte überprüfen sie ihre Eingabe!");
            return false;
        }
    }

    /**
     * Prüft ob es sich um eine gültige Eingabe handelt
     * 
     * @return true wenn die Eingabe von der vorm 600 oder 6,00 für 6 Euro
     */
    private boolean gueltigeEingabe(String eingabe){
        if (eingabe.matches("(\\d+)|(\\d+,\\d+)")){
            return true;
        }
        return false;
    }

    /**
     * Berechnet aus einer Eingabe den Dazugehörigen Geldbetrag aus
     * 
     * @return gibt den jeweiliegen Geldbetrag aus
     */
    private Geldbetrag eingabeZuGeldbetrag(String eingabe){
        Geldbetrag eingabeBetrag;
        
        if (eingabe.matches("(\\d+)")){
            String eingegebenerBetrag = _ui.getTextField().getText();
            int centAnteil = (int)(Integer.parseInt(eingegebenerBetrag))/100;
            int euroAnteil = (int)(Integer.parseInt(eingegebenerBetrag))%100;
            eingabeBetrag = Geldbetrag.select(centAnteil,euroAnteil);
        }
        else {
            String eingegebenerBetrag = _ui.getTextField().getText();
            System.out.println("Erster String: " + eingegebenerBetrag.substring(0, eingegebenerBetrag.indexOf(',')-1)+ " Zweiter String: " + eingegebenerBetrag.substring(eingegebenerBetrag.indexOf(','),eingegebenerBetrag.length()-1));
            int centAnteil = (int)(Integer.parseInt(eingegebenerBetrag.substring(0, eingegebenerBetrag.indexOf(','))));
            int euroAnteil = (int)(Integer.parseInt(eingegebenerBetrag.substring(eingegebenerBetrag.indexOf(',')+1,eingegebenerBetrag.length())));
            eingabeBetrag = Geldbetrag.select(centAnteil,euroAnteil);
        }
        return eingabeBetrag;
    }
}
