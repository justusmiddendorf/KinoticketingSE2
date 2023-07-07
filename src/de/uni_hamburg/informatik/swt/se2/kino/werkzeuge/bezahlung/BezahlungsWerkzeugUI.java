package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Geldbetrag;

/**
 * * Die GUI des {@link BezahlungsWerkzeug}.
 * 
 * @author Wir
 */
public class BezahlungsWerkzeugUI {

    private JDialog _modal;
    private JButton _okayButton;
    private JButton _cancelButton;
    private Geldbetrag _geldbetrag;
    private JTextField _textField;
    private JLabel _errorLabel;

    public BezahlungsWerkzeugUI(Geldbetrag geldbetrag) {
        _geldbetrag = geldbetrag;
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
        dialog.setTitle("Barzahlung");
        dialog.setModal(true);

        _textField = new JTextField("0,00");
        dialog.add(_textField);

        _errorLabel = new JLabel();
        _errorLabel.setForeground(Color.RED);
        dialog.add(_errorLabel);

        JLabel _gesammtSumme = new JLabel("Zuzahlender Betrag: " + _geldbetrag.getFormartiertenBetrag());
        dialog.add(_gesammtSumme, BorderLayout.CENTER);

        _okayButton = new JButton("OK");
        dialog.add(_okayButton, BorderLayout.SOUTH);

        _cancelButton = new JButton("Abbruch");
        dialog.add(_cancelButton, BorderLayout.SOUTH);
        // JTextField textField = new JTextField();
        // textField.setSize(300, 20);
        // dialog.add(textField, BorderLayout.CENTER);

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
     * Gibt das Textfeldes zurück
     * 
     * @return JTextField.
     */
    public JTextField getTextField() {
        return _textField;
    }

    /**
     * Gibt das ErrorLabel zurück
     * 
     * @return JLabel
     */
    public JLabel getErrorLabel() {
        return _errorLabel;
    }
}
