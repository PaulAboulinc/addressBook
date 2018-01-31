package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import Controller.Ajouter;
import Controller.ComboBoxListener;
import Controller.ContactsController;
import Controller.ContactsListListener;
import Controller.Enregistrer;
import Controller.InfoDocumentListener;
import Controller.PopUpListener;
import Controller.Supprimer;
import Controller.WindowsListener;

/**
 * Cette classe permet d'afficher la fenetre principale de l'application.
 * 
 * @author Paul ABOULINC
 *
 */
public class AdresseBook extends JFrame {

	private static final long serialVersionUID = -2218005713453392641L;
	/**
	* Controller de l'application.
	*/
	private ContactsController contactsController = new ContactsController();
	/**
	* RessourceBundle contenant le fichier de la langue utilisée en temps réel.
	*/
	private ResourceBundle messages;
	/**
	* JList contenant les noms des contacts.
	*/
	private JList<String> jListNoms = new JList<String>(contactsController.getListNoms());	
	/**
	* JMenu "Fichier".
	*/
	private JMenu fileMenu = new JMenu();
	/**
	* JMenu "Contacts".
	*/
	private JMenu contactsMenu = new JMenu();
	/**
	* JMenuItem "Enregistré" placé dans "Fichier".
	*/
	private JMenuItem saveMenuItem;
	/**
	* JMenuItem "Ajouter" placé dans "Contacts".
	*/
	private JMenuItem addMenuItem;
	/**
	* JMenuItem "Supprimer" placé dans "Contacts".
	*/
	private JMenuItem deleteMenuItem;
	/**
	* JMenuItem "Supprimer" utilisé dans le menu contextuel.
	*/
	private JMenuItem deletePopupItem;	
	/**
	* La toolBar de l'application.
	*/
	private JToolBar toolBar = new JToolBar("Toolbar");
	/**
	* la bouton "Ajouter" utilisé dans la toolBar.
	*/
	private JButton addBtn = new JButton();
	/**
	* la bouton "Supprimer" utilisé dans la toolBar.
	*/
	private JButton deleteBtn = new JButton();
	/**
	* la bouton "Enregistrer" utilisé dans la toolBar.
	*/
	private JButton saveBtn = new JButton();
	/**
	* L'object PopupMenu contant le JMenuItem permettant de supprimer un contact.
	*/
	private JPopupMenu popUpMenu = new JPopupMenu();
	/**
	* La comboBox contenant les langues utilisables par l'application.
	*/
	private JComboBox<String> comboBox = new JComboBox<String>();
	/**
	* La properties content les locales en fonction des langues.
	*/
	private Properties languages = new Properties();
	/**
	* Image pour "Ajouter".
	*/
	private BufferedImage addImagePath;
	/**
	* Image pour "Supprimer".
	*/
	private BufferedImage deleteImagePath;
	/**
	* Image pour "Enregistrer".
	*/
	private BufferedImage saveImagePath;
	/**
	* TextPane contenant le numéro du contact.
	*/
	private JTextPane numeroContacts = new JTextPane();
	/**
	* TextPane contenant l'adresse du contact.
	*/
	private JTextPane adresseContacts = new JTextPane();
	/**
	* TextPane contenant l'email du contact.
	*/
	private JTextPane emailContacts = new JTextPane();	
	/**
	* JLabel correspondant au numéro du contact.
	*/
	private JLabel numeroLabel = new JLabel();
	/**
	* JLabel correspondant a l'adresse du contact.
	*/
	private JLabel adresseLabel = new JLabel();
	/**
	* JLabel correspondant a l'email du contact.
	*/
	private JLabel emailLabel = new JLabel();
	
	
	/**
	* Initialise l'application.
	*/
	public void init() throws IOException {
			
		addImagePath = ImageIO.read(getClass().getResource("/res/Add16.gif"));
		deleteImagePath = ImageIO.read(getClass().getResource("/res/Delete16.gif"));
		saveImagePath = ImageIO.read(getClass().getResource("/res/Save16.gif"));
		
		iconsInit();
		initMenus();
		initAdresseBookPanel();
		initToolBar();
		initListener();
		initInternationalisation();
		contactsController.setChange(false);
		getSaveBtn().setEnabled(false);
	}
	
	/**
	* Initialise les JMenuItem de l'application.
	*/
	private void iconsInit () {
		saveMenuItem = new JMenuItem(new ImageIcon(saveImagePath));
		addMenuItem = new JMenuItem(new ImageIcon(addImagePath));
		deleteMenuItem = new JMenuItem(new ImageIcon(deleteImagePath));
		deletePopupItem = new JMenuItem(new ImageIcon(deleteImagePath));
	}
	
	/**
	* Initialise le menu de l'application.
	*/
	private void initMenus() {
		JMenuBar menuBar = new JMenuBar();
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.CTRL_MASK));
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,ActionEvent.CTRL_MASK));
		fileMenu.add(saveMenuItem);
		contactsMenu.add(addMenuItem);
		contactsMenu.add(deleteMenuItem);
		getPopUpMenu().add(deletePopupItem);
		menuBar.add(fileMenu);
		menuBar.add(contactsMenu);
		setJMenuBar(menuBar);
	}	
	
	/**
	* Initialise les pannels principaux de l'application :
	* 	- La JList qui affiche les noms des contacts
	* 	- Les TextPanes qui permettent d'afficher et modifier les informations des contacts
	* 	- Les labels des TextPanes
	*/
	private void initAdresseBookPanel() {
		
		JScrollPane numeroScrollPane = new JScrollPane(numeroContacts);
		JScrollPane adresseScrollPane = new JScrollPane(adresseContacts);
		JScrollPane emailScrollPane = new JScrollPane(emailContacts);
		JScrollPane nomsContactsScrollPane = new JScrollPane(getNoms());
		
		JPanel infosPanel = new JPanel();
		infosPanel.setLayout(new GridLayout(0,1));
		numeroScrollPane.setPreferredSize(new Dimension(600, 50));
		adresseScrollPane.setPreferredSize(new Dimension(600, 50));
		emailScrollPane.setPreferredSize(new Dimension(600, 50));
		infosPanel.add(numeroScrollPane);
		infosPanel.add(adresseScrollPane);
		infosPanel.add(emailScrollPane);
		
		JPanel infosEtLabelPanel = new JPanel();
		JPanel labelPanel = new JPanel(new GridLayout(0,1));
		infosEtLabelPanel.setLayout(new BorderLayout());
		labelPanel.add(numeroLabel);
		labelPanel.add(adresseLabel);
		labelPanel.add(emailLabel);
		infosEtLabelPanel.add(labelPanel, BorderLayout.WEST);		
		infosEtLabelPanel.add(infosPanel);
		
		JPanel adresseBookPanel = new JPanel();
		adresseBookPanel.setLayout(new BorderLayout());
		adresseBookPanel.add(nomsContactsScrollPane);
		adresseBookPanel.add(infosEtLabelPanel, BorderLayout.SOUTH);
		add(adresseBookPanel);
	}
	
	/**
	* Initialise la toolBar de l'application.
	*/
	private void initToolBar() {
		fillComboBox();
		comboBox.setSelectedItem("français");
		comboBox.setMaximumSize(new Dimension(200,200));
		getSaveBtn().setIcon(new ImageIcon (saveImagePath));
		addBtn.setIcon(new ImageIcon (addImagePath));
		deleteBtn.setIcon(new ImageIcon (deleteImagePath));
		toolBar.add(getSaveBtn());
		toolBar.add(addBtn);
		toolBar.add(deleteBtn);
		toolBar.add(comboBox);
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.SOUTH);
	}
	
	/**
	* Ajoute les listeners de la fenêtre.
	*/
	private void initListener() {
		getNoms().addMouseListener(new PopUpListener(this));
		saveMenuItem.addActionListener(new Enregistrer(this, contactsController));
		addMenuItem.addActionListener(new Ajouter(this, contactsController));
		deletePopupItem.addActionListener(new Supprimer(this, contactsController));
		deleteMenuItem.addActionListener(new Supprimer(this, contactsController));
		getSaveBtn().addActionListener(new Enregistrer(this, contactsController));
		addBtn.addActionListener(new Ajouter(this, contactsController));
		deleteBtn.addActionListener(new Supprimer(this, contactsController));
		//infosContacts.getDocument().addDocumentListener(new InfoDocumentListener(this, contactsController));
		numeroContacts.getDocument().addDocumentListener(new InfoDocumentListener(this, contactsController));
		adresseContacts.getDocument().addDocumentListener(new InfoDocumentListener(this, contactsController));
		emailContacts.getDocument().addDocumentListener(new InfoDocumentListener(this, contactsController));
		getNoms().addListSelectionListener(new ContactsListListener(this, contactsController));
		comboBox.addActionListener(new ComboBoxListener(this));
		this.addWindowListener(new WindowsListener(this, contactsController));
	}
	
	/**
	* Récupère les valeurs des TextPanes et les mets en forme pour les ajouts au fichier Properties
	* @return Chaine de caractères
	*/
	public String getStringValueOfContactInfo() {
		String numero = numeroContacts.getText();
		String adresse = adresseContacts.getText();
		String email = emailContacts.getText();
		return numero+"|"+adresse+"|"+email;
	}
	
	/**
	 * Renvoie les erreurs les erreurs des champs contacts de la fenetre principale
	 * 
	 * @return une chaine de caractère
	 */
	public String isTextValid() {
		String numero = numeroContacts.getText();
		String adresse = adresseContacts.getText();
		String email = emailContacts.getText();
		String erreurs = getErrorsForMainString(numero, adresse, email);
		return erreurs;
	}
	
	/**
	 * Récupère les erreurs des champs contacts de la fenetre principale
	 * 
	 * @param numero Le numéro du contact
	 * @param adresse L'adresse du contact
	 * @param email L'email du contact
	 * @return chaine de caractères
	 */
	public String getErrorsForMainString(String numero, String adresse, String email) {
		boolean varDoNotContainsPipe = !numero.contains("|") && !adresse.contains("|") & !email.contains("|");
		boolean emailContainAt = email.contains("@");
		
		String affichage="";
		if (numero.length()<11) {	
			try {
				if (numero != null && !numero.isEmpty()) {
					Long.parseLong(numero);		
				}
			} catch (NumberFormatException e2) {
				affichage += getStringFromBundle("Numero_Int")+"\n";
			}
		} else {
			affichage += getStringFromBundle("Numero_Trop_Long")+"\n";
		}
		if (!varDoNotContainsPipe) {
			affichage += getStringFromBundle("Caractere_Interdit")+"\n";
		} 
		if (!emailContainAt && !email.isEmpty()) {
			affichage += getStringFromBundle("Erreur_Email")+"\n";
		}
		return affichage;
	}
	
	/**
	* Initialise la langue de l'application au lancement
	*/
	private void initInternationalisation() {
		String language = System.getProperty("user.language");
		String country = System.getProperty("user.country");
		Locale currentLocale = new Locale(language,country);
		setAllText(currentLocale);
	}
	
	/**
	* Actualise la JList en fonction de la liste du model
	* Actualise également l'affichage du bouton sauvegarder
	*/
	public void setListData() {
		contactsController.setList();
		contactsController.setEnable(getSaveBtn());
		getNoms().setListData(contactsController.getListNoms());
	}

	/**
	* Actualise l'affichage des informations des TextPanes
	* @param infosText Chaine de caractères contenant deux pipes séparants les trois valeurs (nom|adresse|email)
	* @return description Return
	*/
	public void setInfosText(String infosText) {
		String[] infos = infosText.split(Pattern.quote("|"));
		String numero = infos[0];
		String adresse = infos[1];
		String email = infos[2];
		numeroContacts.setText("");
		adresseContacts.setText("");
		emailContacts.setText("");
		numeroContacts.setText(numero);
		adresseContacts.setText(adresse);
		emailContacts.setText(email);
	}

	/**
	* Récupère les boutons de sauvegarde
	* @return JButton
	*/
	public JButton getSaveBtn() {
		return saveBtn;
	}

	/**
	* Récupère la JList
	* @return JList
	*/
	public JList<String> getNoms() {
		return jListNoms;
	}

	/**
	* Récupère le JPoppupMenu permettant la suppression par clique droit
	* @return Chaine de caractères
	*/
	public JPopupMenu getPopUpMenu() {
		return popUpMenu;
	}
	
	/**
	* Remplis la comboBox avec les locales détectées dans le fichier "languages.properties"
	* Crée aussi une properties permettant de stocker les locals en fonction du nom de la langue (nomLangue => local)
	*/
	private void fillComboBox() {
		ResourceBundle langues = ResourceBundle.getBundle("Languages");
		for (Object langue : langues.keySet()) {
			Locale locale = new Locale.Builder().setLanguageTag((String) langue).build();
			languages.put(locale.getDisplayLanguage(locale), langue);
			comboBox.addItem(locale.getDisplayLanguage(locale));
		}
	}
	
	/**
	* Change la langue de l'application en récupérant la local à partir du nom de la langue affiché (utilise la properties initialisé dans fillComboBox())
	* param localeDisplayName Chaine de caractères correspondant au nom de la langue
	*/
	public void setAllTextFromString(String localeDisplayName) {
		String localeString = languages.getProperty(localeDisplayName);
		Locale locale = new Locale.Builder().setLanguageTag(localeString).build();
		setAllText(locale);
	}
	
	/**
	* Modifier la langue de l'application en fonction de la Locale donnée en paramètre
	* param locale Locale
	*/	
	private void setAllText(Locale locale) {
		messages = ResourceBundle.getBundle("MessagesBundle",locale);
		
		fileMenu.setText(messages.getString("Fichier"));
		contactsMenu.setText(messages.getString("Contacts"));
		addBtn.setText(messages.getString("Ajouter"));
		deleteBtn.setText(messages.getString("Supprimer"));
		saveBtn.setText(messages.getString("Enregistrer"));
		setTitle(messages.getString("Titre"));
		saveMenuItem.setText(messages.getString("Enregistrer"));
		addMenuItem.setText(messages.getString("Ajouter"));
		deleteMenuItem.setText(messages.getString("Supprimer"));
		deletePopupItem.setText(messages.getString("Supprimer"));
		numeroLabel.setText(messages.getString("Numero"));
		adresseLabel.setText(messages.getString("Adresse"));
		emailLabel.setText(messages.getString("Email"));
	}
	
	/**
	* Récupère pour une clé, la valeur correspondante dans le RessourceBundle message
	* @param key La clé
	* @return Chaine de caractères
	*/
	public String getStringFromBundle(String key) {
		return messages.getString(key);
	}
	
	/**
	 * Fonction qui renvoie les erreus trouvées ou un chaine de caractères vide
	 * 
	 * @param nom Le nom du contact
	 * @param numero Le numéro du contact
	 * @param adresse L'adresse du contact
	 * @param email L'email du contact
	 * @return chaines de caracteres
	 */
	public String getErrorsString(String nom, String numero, String adresse, String email) {
		boolean varIsNotNull = nom != null && numero != null && adresse != null && email != null;
		boolean varIsNotEmpty = !nom.trim().isEmpty() && !numero.trim().isEmpty() && !adresse.trim().isEmpty() && !email.trim().isEmpty();
		boolean varDoNotContainsPipe = !numero.contains("|") && !adresse.contains("|") & !email.contains("|");
		boolean emailContainAt = email.contains("@");
		
		String affichage="";
		if (numero.length()<11) {			
			try {
				Long.parseLong(numero);		
			} catch (NumberFormatException e2) {
				affichage += getStringFromBundle("Numero_Int")+"\n";
			}
		} else {
			affichage += getStringFromBundle("Numero_Trop_Long")+"\n";
		}
		if (!varDoNotContainsPipe) {
			affichage += getStringFromBundle("Caractere_Interdit")+"\n";
		} 
		if (!varIsNotEmpty || !varIsNotNull) {
			affichage += getStringFromBundle("Champ_Non_Valide")+"\n";
		}
		if (!emailContainAt) {
			affichage += getStringFromBundle("Erreur_Email")+"\n";
		}
		return affichage;
	}
	
	/**
	 * Methode main lancé au démarrage de l'application.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		AdresseBook book = new AdresseBook();
		book.init();
		book.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		book.setSize(500, 500);
		book.setLocation(200, 200);
		book.setVisible(true);
		
	}
}
