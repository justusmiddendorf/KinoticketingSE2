package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * * Die GUI des {@link BezahlungsWerkzeug}.
 * 
 * @author Wir
 */
public class BezahlungsWerkzeugUI {

    private JDialog _modal;
    private JButton _okayButton;
    private JButton _cancelButton;
    private JLabel _preisLabel;
    private JLabel _preisDifferenzLabel;
    private JTextField _textField;

    public BezahlungsWerkzeugUI() {
        _modal = erstelleDialog();
    }

    /**
     * Erstellt den Dialog fürs Bezahlen
     * 
     * @return JDialog der Dialog der angezeigt wird
     */
    private JDialog erstelleDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(10, 3));

        JLabel label = new JLabel("Barzahlung");
        dialog.add(label, BorderLayout.NORTH);

        _preisLabel = new JLabel("Summe: ");
        dialog.add(_preisLabel, BorderLayout.NORTH);

        _preisDifferenzLabel = new JLabel("Restbetrag: ");
        dialog.add(_preisDifferenzLabel, BorderLayout.NORTH);

        _okayButton = new JButton("OK");
        dialog.add(_okayButton, BorderLayout.SOUTH);

        _cancelButton = new JButton("Abbruch");
        dialog.add(_cancelButton, BorderLayout.SOUTH);

        _textField = new JTextField();
        _textField.setSize(200, 20);
        dialog.add(_textField, BorderLayout.CENTER);

        return dialog;
    }

    /**
     * Öffnen des Dialoges
     */
    public void oeffneDialog() {
        _modal.setVisible(true);
    }

    /**
     * Gibt den CancelButton zurück.
     * 
     * @return CancelButton
     */
    public JButton getCancelButton() {
        return _cancelButton;
    }

    /**
     * Gibt den OkayButton zurück.
     * 
     * @return OkayButton
     */
    public JButton getokayButton() {
        return _okayButton;
    }

    /**
     * Gibt das Modal zurück
     * 
     * @return Modal gibt das Modal zurück
     */
    public JDialog getModal() {
        return _modal;
    }

    /**
     * Gibt den aktuellen Preis zurück
     * 
     * @return Label gibt das Preis Label zurück
     */
    public JLabel getPreisLabel() {
        return _preisLabel;
    }

    /**
     * Gibt das Textfield zurück
     * 
     * @return Textfield gibt das Text Feld zurück
     */
    public JTextField getTextField() {
        return _textField;
    }

    /**
     * Gibt die differenz der eingabe zum Preis zurück
     * 
     * @return Label gibt das Differenz Preis Label zurück
     */
    public JLabel getPreisDifferenzLabel() {
        return _preisDifferenzLabel;
    }
}
