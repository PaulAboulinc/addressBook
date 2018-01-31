package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.ContactsController;

/**
 * 
 * Fenetre permettant la création d'un contact
 * 
 * @author ABOULINC
 *
 */
public class CreationContact extends JFrame implements ActionListener{


	private static final long serialVersionUID = 5686636190803271047L;
	/**
	 * JTextField contenant le nom du contact.
	 */
	private JTextField nomContact;
	/**
	 * JTextField contant le numéro du contact.
	 */
	private JTextField numeroContact;
	/**
	 * JTextField contenant l'adresse du contact.
	 */
	private JTextField adresseContact;
	/**
	 * JTextField contenant l'email du contact.
	 */
	private JTextField emailContact;
	/**
	 * JButton permettant de valider la création du contact
	 */
	private JButton btValider;
	/**
	 * Instance du controller
	 */
	private ContactsController contactsController;
	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook book;
	
	/**
	 * Constructeur de la classe CreationContact
	 * @param b AdresseBook
	 * @param c ContactsController
	 */
	public CreationContact(AdresseBook b, ContactsController c) {	
		contactsController = c;
		book = b;
		init();
	}
	
	/**
	 * Initialise la fenetre
	 */
	public void init() {
		setTitle(book.getStringFromBundle("Creation_Contact"));
		setLayout(new FlowLayout());

		JLabel labNom = new JLabel(book.getStringFromBundle("Nom"));
		JLabel labNumero = new JLabel(book.getStringFromBundle("Numero"));
		JLabel labAdresse = new JLabel(book.getStringFromBundle("Adresse"));
		JLabel labEmail = new JLabel(book.getStringFromBundle("Email"));

		add(labNom);
		nomContact = new JTextField(15);
		add(nomContact);
		add(labNumero);
		numeroContact = new JTextField(15);
		add(numeroContact);
		add(labAdresse);
		adresseContact = new JTextField(15);
		add(adresseContact);
		add(labEmail);
		emailContact = new JTextField(15);
		add(emailContact);

		btValider = new JButton(book.getStringFromBundle("Valider"));
		add(btValider);

		btValider.addActionListener(this);
		
		setLocation(300, 300);
		setResizable(false);
		setSize(200, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Ajoute le contact ou affiche les erreurs.
	 * Verifie que les champs soient non nulls, non vides et qu'ils ne contiennent pas de "|".
	 */
	public void actionPerformed(ActionEvent e) {
		String nom = nomContact.getText();
		String numero = numeroContact.getText();
		String adresse = adresseContact.getText();
		String email = emailContact.getText();

		String affichage = book.getErrorsString(nom, numero, adresse, email);
		
		if (affichage.isEmpty()) {
			contactsController.ajouterContacts(nom, numero+"|"+adresse+"|"+email);
			book.setListData();
			this.dispose();
		} else {
			new FenetreAffichage(affichage, book);
		}
	}

}