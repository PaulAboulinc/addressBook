package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.AdresseBook;
import View.CreationContact;

/**
 * La classe Ajouter écoute l'action d'ajout
 * 
 * @author ABOULINC
 *
 */
public class Ajouter implements ActionListener {

	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook book;
	/**
	 * Instance du controller de l'application
	 */
	private ContactsController contactsController;
	
	/**
	 * Constructeur de la classe Ajouter
	 * @param b Instance AdresseBook
	 * @param c Instance ContactsController
	 */
	public Ajouter(AdresseBook b, ContactsController c) {
		contactsController = c;
		book = b;
	}

	/**
	 * Crée une fenetre "CreationContact" qui contient le formulaire de création.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new CreationContact(book, contactsController);
	}
}
