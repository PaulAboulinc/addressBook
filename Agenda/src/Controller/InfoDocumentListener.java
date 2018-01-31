package Controller;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import View.AdresseBook;
import View.FenetreAffichage;

/**
 * La classe InfoDocumentListener écoute les modifications faites sur les JTextPane
 * 
 * @author ABOULINC
 *
 */
public class InfoDocumentListener implements DocumentListener {

	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook adresseBook;
	/**
	 * Instance du controller de l'application
	 */
	private ContactsController contactsController;
	
	/**
	 * Constructeur de la classe InfoDocumentListener
	 * @param b Instance AdresseBook
	 * @param c Instance ContactsController
	 */
	public InfoDocumentListener(AdresseBook b, ContactsController c) {
		adresseBook = b;
		contactsController = c;
	}
	
	/**
	 * Appelle la fonction permettant de modifier la properties "contacts" du model
	 * Actualise le bouton "Enregistrer"
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		highlight(e);
	}
	
	/**
	 * Appelle la fonction permettant de modifier la properties "contacts" du model
	 * Actualise le bouton "Enregistrer"
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		highlight(e);
	}

	/**
	 * Appelle la fonction permettant de modifier la properties "contacts" du model
	 * Actualise le bouton "Enregistrer"
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		highlight(e);
	}
	
	/**
	 * Supprime les erreurs s'il y en a
	 * 
	 * @param e documentEvent
	 */
	private void highlight(DocumentEvent e) {
	    Runnable doHighlight = new Runnable() {
	        @Override
	        public void run() {
	        	if (!updateContact()) {
	    			try {
	    				e.getDocument().remove(e.getOffset(), e.getLength());
	    			} catch (BadLocationException e1) {
	    				e1.printStackTrace();
	    			}
	    		}
	        }
	    };       
	    SwingUtilities.invokeLater(doHighlight);
	}
	
	/**
	 * Fonction que permet la mise a jour de la properties contacts tout en vérifiant s'il y a une erreur
	 * 
	 * @return un boolean
	 */
	private boolean updateContact() {
		String erreurs = adresseBook.isTextValid();
		if (erreurs.isEmpty()) {
			contactsController.modifierContact(adresseBook.getStringValueOfContactInfo());
			contactsController.setEnable(adresseBook.getSaveBtn());
			return true;
		} else {
			new FenetreAffichage(erreurs, adresseBook);
			return false;
		}
	}

}
