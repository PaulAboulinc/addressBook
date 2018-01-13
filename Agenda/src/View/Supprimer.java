package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.ContactsController;

public class Supprimer implements ActionListener {

	ContactsController contactsController;
	AdresseBook book;

	public Supprimer(AdresseBook b, ContactsController c) {
		contactsController = c;
		book = b;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		contactsController.supprimerContacts();
		book.setListData();
	}
}
