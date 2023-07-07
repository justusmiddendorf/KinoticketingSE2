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

        JButton okayButton = new JButton("OK");
        dialog.add(okayButton, BorderLayout.SOUTH);

        JButton cancelButton = new JButton("Abbruch");
        dialog.add(cancelButton, BorderLayout.SOUTH);
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
}
