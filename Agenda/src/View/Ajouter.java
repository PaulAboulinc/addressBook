package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JOptionPane;

import Controller.ContactsController;

public class Ajouter implements ActionListener {

	ContactsController contactsController;

	public Ajouter(ContactsController c) {
		contactsController = c;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nom = JOptionPane.showInputDialog("Entrez le nom souhaité : ");
		String valeur="";
		if (nom != null && !nom.trim().isEmpty()) {
			valeur = JOptionPane.showInputDialog("Entrez les informations : ");
		}
		if (valeur != null && !valeur.trim().isEmpty()) {
			contactsController.ajouterContacts(nom, valeur);
		}
	}
}
