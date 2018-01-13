package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.ContactsController;

public class Enregistrer implements ActionListener{
	
	ContactsController contactsController;
	AdresseBook book;
	
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
