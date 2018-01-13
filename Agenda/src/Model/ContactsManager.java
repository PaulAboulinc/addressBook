package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;

public class ContactsManager {
	
	private boolean change;
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
			ajouterP(contacts, (String) s, p.getProperty((String) s));
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

	public void modifierP(Properties p, String key, String value) {
		if (!value.equals(p.getProperty(key)) && !value.isEmpty()) {
			p.setProperty(key, value);
			//setList();
		}
	}
	
	public void modifierContact(String value, String key) {
		if (!value.equals(contacts.getProperty(key)) && !value.isEmpty()) {
			contacts.setProperty(key, value);
			//setList();
		}
	}

	public void ajouterP(Properties p, String key, String value) {
		p.put(key, value);
		listNoms.addElement(key);
		//setList();
	}
	
	public void ajouterContacts(String key, String value) {
		contacts.put(key, value);
		listNoms.addElement(key);
		//setList();
	}

	public void supprimerP(Properties p, String key) {
		p.remove(key);
		listNoms.remove(key);
		//setList();
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
	
	public Properties getContacts() {
		return contacts;
	}
	
	public Vector<String> getListNoms() {
		return listNoms;
	}
	
}
