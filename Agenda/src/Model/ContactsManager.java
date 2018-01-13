package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Properties;
import java.util.Vector;

public class ContactsManager {
	
	private boolean change;
	private String nomSelect;
	private Properties contacts = new Properties();
	private Vector<String> listNoms = new Vector<String>();
	
	public ContactsManager () {
		chargerContacts();
	}
		
	public void chargerContacts() {
		String fileLocation = "contacts.properties";
		Properties p = new Properties();
		try (InputStream in = new FileInputStream(fileLocation)) {
			p.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Object s : p.keySet()) {
			ajouterContacts((String) s, p.getProperty((String) s));
		}
	}

	public void setList() {
		change = true;
		Collections.sort(listNoms);
	}

	public void afficherP(Properties p) {
		for (Object s : p.keySet()) {
			System.out.println(s + " : " + p.getProperty((String) s));
		}
	}
	
	public void ajouterContacts(String key, String value) {
		contacts.put(key, value);
		listNoms.addElement(key);
		setList();
	}
	
	public void modifierContact(String value) {
		if (nomSelect != null) {
			if(!value.equals(contacts.getProperty(nomSelect)) && !value.isEmpty()) {
				contacts.setProperty(nomSelect, value);
				setList();
			}
		}
	}
	
	public void supprimerContacts() {
		if (nomSelect != null) {
			contacts.remove(nomSelect);
			listNoms.remove(nomSelect);
			setList();
		}
	}

	public void enregistrer() {
		String fileLocation = "contacts.properties";
		try (OutputStream out = new FileOutputStream(fileLocation)) {
			contacts.store(out, "fichier properéties");
			change = false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean getChange() {
		return change;
	}
	
	public void setChange(boolean newChange) {
		 change = newChange;
	}
	
	public String getNomSelect() {
		return nomSelect;
	}

	public void setNomSelect(String newNomSelect) {
		nomSelect = newNomSelect;
	}
	
	public Properties getContacts() {
		return contacts;
	}
	
	public Vector<String> getListNoms() {
		return listNoms;
	}	
}
