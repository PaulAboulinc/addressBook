package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.ContactsController;
import View.AdresseBook;

public class Enregistrer implements ActionListener{
	
	private ContactsController contactsController;
	private AdresseBook book;
	
	public Enregistrer(AdresseBook b, ContactsController c) {
		contactsController=c;
		book = b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		contactsController.enregistrer();
		book.saveBtn.setEnabled(false);
	}
}
