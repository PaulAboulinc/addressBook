package Controller;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
			try {						
				contactsController.setNomSelect(item.getSelectedValue());
				book.setInfosText(contactsController.getPropertyValue(item.getSelectedValue()));
			} catch (Exception ex) {
				book.getNoms().setSelectedIndex(0);
			}
			
		}
	}

}
