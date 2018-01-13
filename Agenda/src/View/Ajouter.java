package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Controller.ContactsController;

public class Ajouter implements ActionListener {

	ContactsController contactsController;
	AdresseBook book;

	public Ajouter(AdresseBook b, ContactsController c) {
		contactsController = c;
		book = b;
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
			book.setListData();
		}
	}
}
