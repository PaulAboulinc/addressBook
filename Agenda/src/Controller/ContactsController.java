package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;

import Model.ContactsManager;

public class ContactsController {
	
	private ContactsManager manager = new ContactsManager();
		
	public void chargerContacts() {
		manager.chargerContacts();
	}

	public void setList() {
		manager.setList();
	}

	public void afficherP(Properties p) {
		manager.afficherP(p);
	}

	public void modifierP(Properties p, String key, String value) {
		manager.modifierP(p, key, value);
	}
	
	public void modifierContact(String value, String key) {
		manager.modifierContact(value, key);
	}

	public void ajouterP(Properties p, String key, String value) {
		manager.ajouterP(p, key, value);
	}
	
	public void ajouterContacts(String key, String value) {
		manager.ajouterContacts(key, value);
	}

	public void supprimerP(Properties p, String key) {
		manager.supprimerP(p, key);
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
	
	public Properties getContacts() {
		return manager.getContacts();
	}
	
	public Vector<String> getListNoms() {
		return manager.getListNoms();
	}
}
