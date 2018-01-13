package View;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowsListener extends JFrame implements WindowListener {

	AdresseBook book;

	public WindowsListener(AdresseBook adresseBook) {
		book = adresseBook;
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (book.getChange()) {
			Object[] options = { "Oui", "Non", "Annuler" };
			int n = JOptionPane.showOptionDialog(this,
					"Sauvegarder les changements ?", "Selectionner une option",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, // do not use a custom
														// Icon
					options, // the titles of buttons
					options[0]); // default button title
			if (n == 0) {
				book.enregistrer();
				book.dispose();
				System.exit(0);
			} else {
				if (n == 1) {
					book.dispose();
					System.exit(0);
				}
			}
		} else {
			book.dispose();
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

}
