package Controller;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.omg.CORBA.ExceptionList;

import View.AdresseBook;

/**
 * La classe ContactsListListener écoute les selections sur la JList
 * 
 * @author ABOULINC
 *
 */
public class ContactsListListener implements ListSelectionListener{

	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook book;
	/**
	 * Instance du controller de l'application
	 */
	private ContactsController contactsController;
	
	/**
	 * Constructeur de la classe ContactsListListener
	 * @param b Instance AdresseBook
	 * @param c Instance ContactsController
	 */
	public ContactsListListener(AdresseBook b, ContactsController c) {
		book = b;
		contactsController = c;
	}	

	/**
	 * Modifier dans le modele le nom selectionné
	 * Actualise l'affichage du contacts
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<String> item = (JList<String>) e.getSource();
		if (!e.getValueIsAdjusting()) {
			try {						
				contactsController.setNomSelect(item.getSelectedValue());
				book.setInfosText(contactsController.getPropertyValue(item.getSelectedValue()));
			} catch (NullPointerException ex) {
				book.getNoms().setSelectedIndex(0);
			}
			//contactsController.setChange(false);
			//contactsController.setEnable(book.getSaveBtn());
		}
	}

}
