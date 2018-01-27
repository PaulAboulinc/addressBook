package Model;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.ContactsController;
import View.AdresseBook;


public class ContactsListListenener implements ListSelectionListener{

	private AdresseBook book;
	private ContactsController contactsController;
	
	public ContactsListListenener(AdresseBook b, ContactsController c) {
		book = b;
		contactsController = c;
	}	
	//todo : nom géré dans le model directement
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<String> item = (JList<String>) e.getSource();
		if (!e.getValueIsAdjusting()) {
			for (Object s : contactsController.getContacts().keySet()) {
				if (s.equals(item.getSelectedValue())) {
					contactsController.setNomSelect((String) s);
					book.infos.setText(contactsController.getContacts().getProperty((String) s));
				}
			}
		}
	}

}
