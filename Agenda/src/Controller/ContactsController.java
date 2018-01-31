package Controller;

import java.util.Vector;

import javax.swing.JButton;

import Model.ContactsManager;

/**
 * La classe ContactsController est le controller de l'application
 * 
 * @author ABOULINC
 *
 */
public class ContactsController {
	
	/**
	 * Une instance de "ContactsManager"
	 */
	private ContactsManager manager = new ContactsManager();

	/**
	 * Appelle la fonction setList de ContactsManager.
	 */
	public void setList() {
		manager.setList();
	}
	
	/**
	 * Appelle la fonction modifierContact de ContactsManager
	 * @param value est la nouvelle valeur
	 */
	public void modifierContact(String value) {
		manager.modifierContact(value);
	}

	/**
	 * Appelle la fonction ajouterContacts de ContactsManager
	 * @param key est la clé
	 * @param value est la valeur
	 */
	public void ajouterContacts(String key, String value) {
		manager.ajouterContacts(key, value);
	}

	/**
	 * Appelle la fonction supprimerContacts de ContactsManager qui renvoie false s'il n'y a pas de contact selectionné
	 * @return un boolean
	 */
	public boolean supprimerContacts() {
		return manager.supprimerContacts();
	}

	/**
	 * Appelle la fonction enregistrer de ContactsManager.
	 */
	public void enregistrer() {
		manager.enregistrer();
	}
	
	/**
	 * Récupère la valeur du boolean "Change" depuis ContactsManager
	 * @return un boolean
	 */
	public boolean getChange() {
		return manager.getChange();
	}
	
	/**
	 * Modifier la valeur du boolean "Change"
	 * @param newChange la nouvelle valeur
	 */
	public void setChange(boolean newChange) {
		manager.setChange(newChange);
	}
	
	/**
	 * Récupère le nom selectionné
	 * @return une chaine de caractères
	 */
	public String getNomSelect() {
		return manager.getNomSelect();
	}
	
	/**
	 * Modifie le dernier nom selectionné
	 * @param newNomSelect le nouveau nom
	 */
	public void setNomSelect(String newNomSelect) {
		manager.setNomSelect(newNomSelect);
	}
	
	/**
	 * Récupère la valeur de la properties "contacts" en fonction d'une clé
	 * @param key est la clé
	 * @return une chaine de caractères
	 */
	public String getPropertyValue(String key) {
		return manager.getPropertyValue(key);
	}
	
	/**
	 * Récupère la liste de noms
	 * @return vector de String
	 */
	public Vector<String> getListNoms() {
		return manager.getListNoms();
	}
	
	/**
	 * Modifie l'affichage du bouton "enregistrer" en fonction de la valeur de "change"
	 * @param saveBtn
	 */
	public void setEnable(JButton saveBtn) {
		saveBtn.setEnabled(getChange());
	}
}
