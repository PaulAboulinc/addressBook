package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.AdresseBook;

/**
 * La classe Supprimer écoute une action
 * 
 * @author ABOULINC
 *
 */
public class Supprimer implements ActionListener {

	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook book;
	/**
	 * Instance du controller de l'application
	 */
	private ContactsController contactsController;

	/**
	 * Contructeur de la classe Supprimer
	 * @param b Instance AdresseBook
	 * @param c Instance ContactsController
	 */
	public Supprimer(AdresseBook b, ContactsController c) {
		contactsController = c;
		book = b;
	}
	
	/**
	 * Supprime le contact si l'utilisateur confirme la suppression via la JOptionPane
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nomSelected = contactsController.getNomSelect();
		if (nomSelected != null) {	
			Object[] options = { book.getStringFromBundle("Oui"), book.getStringFromBundle("Non")};
			int n = JOptionPane.showOptionDialog(book,
					book.getStringFromBundle("Supprimer")+" "+nomSelected, book.getStringFromBundle("Selectionner_Option"),
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					options,
					options[0]);
			if (n==0) {			
				contactsController.supprimerContacts();
				book.setListData();
			}
		}
	}
}
