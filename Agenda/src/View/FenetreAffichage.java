package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Fenetre permettant d'afficher les erreurs à l'utilisateur
 * 
 * @author ABOULINC
 *
 */
public class FenetreAffichage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1867316201573144127L;
	/**
	 * JButton permettant de quitter la fenetre
	 */
	private JButton leaveButton;
	/**
	 * JTextArea contenant l'erreur
	 */
	private JTextArea errorTextArea;
	/**
	 * Instance de la fenetre principale
	 */
	private AdresseBook book;
	
	/**
	 * Constructeur de la classe FenetreAffichage.
	 * 
	 * @param texte contenant le texte d'erreur à afficher
	 * @param b AdresseBook
	 */
	public FenetreAffichage(String texte, AdresseBook b) {
		book = b;
		
		leaveButton = new JButton(book.getStringFromBundle("Quitter"));
		errorTextArea = new JTextArea(texte,2,5);
		errorTextArea.setDisabledTextColor(Color.RED);
		errorTextArea.setEnabled(false);
		leaveButton.addActionListener(this);
		add(errorTextArea,BorderLayout.NORTH);
		add(leaveButton, BorderLayout.SOUTH);

		setTitle(book.getStringFromBundle("Erreur"));
		setBounds(500, 500, 400, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Ferme la fenêtre.
	 */
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
