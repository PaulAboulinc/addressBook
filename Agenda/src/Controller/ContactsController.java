package Controller;

import java.util.Vector;

import javax.swing.JButton;

import Model.ContactsManager;

public class ContactsController {
	
	private ContactsManager manager = new ContactsManager();

	public void setList() {
		manager.setList();
	}
	
	public void modifierContact(String value) {
		manager.modifierContact(value);
	}

	public void ajouterContacts(String key, String value) {
		manager.ajouterContacts(key, value);
	}

	public void supprimerContacts() {
		manager.supprimerContacts();
	}

	public void enregistrer() {
		manager.enregistrer();
	}
	
	public boolean getChange() {
		return manager.getChange();
	}
	
	public void setChange(boolean newChange) {
		manager.setChange(newChange);
	}
	
	public String getNomSelect() {
		return manager.getNomSelect();
	}
	
	public void setNomSelect(String newNomSelect) {
		manager.setNomSelect(newNomSelect);
	}
	
	public String getPropertyValue(String key) {
		return manager.getPropertyValue(key);
	}
	
	public Vector<String> getListNoms() {
		return manager.getListNoms();
	}
	
	public void setEnable(JButton saveBtn) {
		saveBtn.setEnabled(getChange());
	}
}
