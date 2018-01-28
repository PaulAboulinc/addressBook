package Controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
		contactsController.modifierContact(adresseBook.getInfosText());
		contactsController.setEnable(adresseBook.getSaveBtn());
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		contactsController.modifierContact(adresseBook.getInfosText());
		contactsController.setEnable(adresseBook.getSaveBtn());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		contactsController.modifierContact(adresseBook.getInfosText());
		contactsController.setEnable(adresseBook.getSaveBtn());
	}

}
