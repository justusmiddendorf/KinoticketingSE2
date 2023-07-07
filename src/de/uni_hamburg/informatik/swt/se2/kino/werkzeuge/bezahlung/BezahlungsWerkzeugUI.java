package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * * Die GUI des {@link BezahlungsWerkzeug}.
 * 
 * @author Wir
 */
public class BezahlungsWerkzeugUI {

    private JDialog _modal;
    private JButton _okayButton;
    private JButton _cancelButton;

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
}
