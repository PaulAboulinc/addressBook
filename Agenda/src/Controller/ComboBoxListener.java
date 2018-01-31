package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import View.AdresseBook;

/**
 * La classe ComboBoxListener écoute un comboBox contenant les différentes langues de l'application
 * 
 * @author ABOULINC
 *
 */
public class ComboBoxListener implements ActionListener {

	/**
	 * Instance de la fenetre principale.
	 */
	private AdresseBook adresseBook;
	
	/**
	 * Constructeur de la classe ComboBoxListener
	 * @param b est une Instance d'AdresseBook
	 */
	public ComboBoxListener(AdresseBook b) {
		adresseBook = b;
	}
	
	/**
	 * Appelle la fonction qui modifie la langue de l'application
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JComboBox<String> item = (JComboBox<String>) arg0.getSource();
		adresseBook.setAllTextFromString(item.getSelectedItem().toString());
	}

}
