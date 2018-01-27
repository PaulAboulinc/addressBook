package Model;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.ContactsController;
import View.AdresseBook;


public class InfoDocumentListener implements DocumentListener {

	private ContactsController contactsController;
	private AdresseBook adresseBook;
	
	public InfoDocumentListener(AdresseBook b, ContactsController c) {
		contactsController = c;
		adresseBook = b;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		contactsController.modifierContact(adresseBook.infos.getText());
		contactsController.setEnable(adresseBook.saveBtn);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		contactsController.modifierContact(adresseBook.infos.getText());
		contactsController.setEnable(adresseBook.saveBtn);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		contactsController.modifierContact(adresseBook.infos.getText());
		contactsController.setEnable(adresseBook.saveBtn);
	}

}
