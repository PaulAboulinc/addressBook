package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.AdresseBook;

public class Supprimer implements ActionListener {

	private ContactsController contactsController;
	private AdresseBook book;

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
