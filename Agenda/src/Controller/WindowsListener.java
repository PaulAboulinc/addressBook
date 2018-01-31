package Controller;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import View.AdresseBook;

/**
 * La classe WindowsListener écoute la fermeture de la fenetre principale
 * 
 * @author ABOULINC
 *
 */
public class WindowsListener extends WindowAdapter {

	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook book;
	/**
	 * Instance du controller de l'application
	 */
	private ContactsController contactsController;

	/**
	 * Constructeur de la classe WindowsListener
	 * @param b Instance AdresseBook
	 * @param c Instance ContactsController
	 */
	public WindowsListener(AdresseBook b, ContactsController c) {
		book = b;
		contactsController = c;
	}

	/**
	 * Permet d'enregistrer les modifications avant la fermeture s'il y a eu des modifications.
	 * Permet aussi d'annuler la fermeture.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if (contactsController.getChange()) {
			Object[] options = { book.getStringFromBundle("Oui"), book.getStringFromBundle("Non"), book.getStringFromBundle("Annuler") };
			int n = JOptionPane.showOptionDialog(book,
					book.getStringFromBundle("Sauvegarder_Changement"), book.getStringFromBundle("Selectionner_Option"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					options,
					options[0]);
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
