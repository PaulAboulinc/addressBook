package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * La classe ContactsManager gère la properties des contacts
 * 
 * @author ABOULINC
 *
 */
public class ContactsManager {
	
	/**
	 * boolean indiquant s'il y a eu un changement depuis le dernier enregistrement.
	 */
	private boolean change;
	/**
	 * String contenant le dernier nom selectionné.
	 */
	private String nomSelect;
	/**
	 * Properties contenant tous les contacts.
	 */
	private Properties contacts = new Properties();
	/**
	 * Liste des noms des contacts.
	 */
	private Vector<String> listNoms = new Vector<String>();
	
	/**
	 * Contructeur de la classe ContactsManager
	 */
	public ContactsManager () {
		chargerContacts();
	}
		
	/**
	 * Charge les contacts dans la liste et de la properties depuis "contacts.properties"
	 */
	private void chargerContacts() {
		String fileLocation = "contacts.properties";
		Properties p = new Properties();
		try (InputStream in = new FileInputStream(fileLocation)) {
			p.load(in);
		} catch (IOException e1) {
			enregistrer();
		}
		for (Object s : p.keySet()) {
			ajouterContacts((String) s, p.getProperty((String) s));
		}
	}

	/**
	 * Passe le boolean "change" à true et trie la liste des noms.
	 */
	public void setList() {
		change = true;
		Collections.sort(listNoms);
	}
	
	/**
	 * Ajoute un contact dans la properties "contacts"
	 * @param key est la clé
	 * @param value est la valeur
	 */
	public void ajouterContacts(String key, String value) {
		contacts.put(key, value);
		listNoms.addElement(key);
		setList();
	}
	
	/**
	 * Permet de récupérer une valeur dans la properties "contacts" en fonction d'une clé.
	 * @param key est la clé
	 * @return Chaine de caractère
	 */
	public String getPropertyValue (String key) {
		return contacts.getProperty(key);
	}
	
	/**
	 * Modifie la properties "contacts" avec pour clé la variable "nomSelect"
	 * @param value est la nouvelle valeur
	 */
	public void modifierContact(String value) {
		if (nomSelect != null) {
			if(!value.equals(contacts.getProperty(nomSelect)) && !valueIsEmpty(value)) {
				contacts.setProperty(nomSelect, value);
				setList();
			}
		}
	}
	
	/**
	 * Vérifie si pour un string  nom|adresse|email  un des attributs est vide.
	 * @param value est la valeur de type nom|adresse|email
	 * @return un boolean
	 */
	private boolean valueIsEmpty(String value) {
		String[] infos = value.split(Pattern.quote("|"));
		if(infos.length<3) {
			return true;
		}
		for (String info : infos) {
			if (info.trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Supprimer le dernier contact selectionné, renvoi false s'il n'y en a pas
	 * @return un boolean
	 */
	public boolean supprimerContacts() {
		if (nomSelect != null) {
			contacts.remove(nomSelect);
			listNoms.remove(nomSelect);
			setList();
			return true;
		}
		return false;
	}

	/**
	 * Enregistre la properties "contacts" dans le fichier "contacts.properties".
	 */
	public void enregistrer() {
		String fileLocation = "contacts.properties";
		try (OutputStream out = new FileOutputStream(fileLocation)) {
			contacts.store(out, "fichier properties");
			change = false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * récupère la valeur du boolean "change"
	 * @return un boolean
	 */
	public boolean getChange() {
		return change;
	}
	
	/**
	 * Change la valeur du boolean "change".
	 * @param newChange La nouvelle valeur
	 */
	public void setChange(boolean newChange) {
		 change = newChange;
	}
	
	/**
	 * Récupère le dernier nom selectionné par l'utilisateur
	 * @return une Chaine de caractère
	 */
	public String getNomSelect() {
		return nomSelect;
	}

	/**
	 * Change la valeur du nom selectionné
	 * @param newNomSelect le nouveau nom selectionné
	 */
	public void setNomSelect(String newNomSelect) {
		nomSelect = newNomSelect;
	}
	
	/**
	 * Permet de récupère la liste des noms
	 * @return un Vector de String
	 */
	public Vector<String> getListNoms() {
		return listNoms;
	}	
	
	/**
	 * Redéfinition de la méthode toString
	 */
	public String toString() {
		String affichage = "---------------------------------Début--------------------------------------------"+"\n";
		for (Object key : contacts.keySet()) {
			affichage += key + " : " + contacts.getProperty((String) key) + "\n";
		}
		affichage += "---------------------------------Fin---------------------------------"+"\n";
		return affichage;
	}
}
