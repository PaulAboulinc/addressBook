package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.AdresseBook;

/**
 * La classe Enregistrer permet d'enregistrer la properties "contacts" dans le fichier prévu pour lors d'une action
 * 
 * @author ABOULINC
 *
 */
public class Enregistrer implements ActionListener{
	
	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook book;
	/**
	 * Instance du controller de l'application
	 */
	private ContactsController contactsController;
	
	/**
	 * Constructeur de la classe Enregistrer
	 * @param b Instance AdresseBook
	 * @param c Instance ContactsController
	 */
	public Enregistrer(AdresseBook b, ContactsController c) {
		contactsController=c;
		book = b;
	}

	/**
	 * Appelle la fonction permettant d'enregistrer
	 * Actualise le bouton "Enregistrer"
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		contactsController.enregistrer();
		book.getSaveBtn().setEnabled(false);
	}
}
