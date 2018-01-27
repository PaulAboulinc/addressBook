package Model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import Controller.ContactsController;
import View.AdresseBook;

public class WindowsListener extends WindowAdapter {

	private AdresseBook book;
	private ContactsController contactsController;

	public WindowsListener(AdresseBook b, ContactsController c) {
		book = b;
		contactsController = c;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (contactsController.getChange()) {
			Object[] options = { "Oui", "Non", "Annuler" };
			int n = JOptionPane.showOptionDialog(book,
					"Sauvegarder les changements ?", "Selectionner une option",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, // do not use a custom
														// Icon
					options, // the titles of buttons
					options[0]); // default button title
			if (n == 0) {
				contactsController.enregistrer();
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
}
