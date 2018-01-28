package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.AdresseBook;

public class Ajouter implements ActionListener {

	private ContactsController contactsController;
	private AdresseBook book;

	public Ajouter(AdresseBook b, ContactsController c) {
		contactsController = c;
		book = b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nom = JOptionPane.showInputDialog(book.getStringFromBundle("Ajout_Nom"));
		String valeur="";
		if (nom != null && !nom.trim().isEmpty()) {
			valeur = JOptionPane.showInputDialog(book.getStringFromBundle("Ajout_Informations"));
		}
		if (valeur != null && !valeur.trim().isEmpty()) {
			contactsController.ajouterContacts(nom, valeur);
			book.setListData();
		}
	}
}
