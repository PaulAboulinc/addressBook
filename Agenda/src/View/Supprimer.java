package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Supprimer implements ActionListener {

	AdresseBook book;

	public Supprimer(AdresseBook b) {
		book = b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		book.supprimerP(book.getContacts(), book.nom);
	}
}
