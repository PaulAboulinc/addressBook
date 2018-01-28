package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import Controller.ContactsController;
import Controller.ContactsListListenener;
import Controller.Enregistrer;
import Controller.InfoDocumentListener;
import Controller.PopUpListener;
import Controller.Supprimer;
import Controller.WindowsListener;

public class AdresseBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContactsController contactsController = new ContactsController();
	private ResourceBundle messages;
	private JList<String> jListNoms = new JList<String>(contactsController.getListNoms());
	private JScrollPane nomsContactsScrollPane = new JScrollPane(getNoms());
	private JTextPane infosContacts = new JTextPane();
	private JScrollPane infosScrollPane = new JScrollPane(infosContacts);
	private JPanel adresseBook = new JPanel();
	private JMenu fileMenu = new JMenu();
	private JMenu contactsMenu = new JMenu();
	private JMenuItem saveMenuItem;
	private JMenuItem addMenuItem;
	private JMenuItem deleteMenuItem;
	private JMenuItem deletePopupItem;
	private JMenuBar menuBar = new JMenuBar();
	private JToolBar toolBar = new JToolBar("Toolbar");
	private JButton addBtn = new JButton();
	private JButton deleteBtn = new JButton();
	private JButton saveBtn = new JButton();
	private JPopupMenu popUpMenu = new JPopupMenu();
	
	
	
	public void init() {
		
		String saveImagePath = "lib/toolbarButtonGraphics/general/Save16.gif";
		String addImagePath = "lib/toolbarButtonGraphics/general/Add16.gif";
		String deleteImagePath = "lib/toolbarButtonGraphics/general/Delete16.gif";
		
		ImageIcon saveIcon = new ImageIcon(saveImagePath);
		ImageIcon addIcon = new ImageIcon(addImagePath);
		ImageIcon deleteIcon = new ImageIcon(deleteImagePath);				
		
		saveMenuItem = new JMenuItem(saveIcon);
		addMenuItem = new JMenuItem(addIcon);
		deleteMenuItem = new JMenuItem(deleteIcon);
		deletePopupItem = new JMenuItem(deleteIcon);
		
		setLayout(new BorderLayout());
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.CTRL_MASK));
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,ActionEvent.CTRL_MASK));
		fileMenu.add(saveMenuItem);
		contactsMenu.add(addMenuItem);
		contactsMenu.add(deleteMenuItem);
		
		getPopUpMenu().add(deletePopupItem);
		menuBar.add(fileMenu);
		menuBar.add(contactsMenu);
		
		adresseBook.setLayout(new BorderLayout());
		adresseBook.add(nomsContactsScrollPane);
		infosScrollPane.setPreferredSize(new Dimension(600, 50));
		adresseBook.add(infosScrollPane, BorderLayout.SOUTH);
		add(adresseBook);
		setJMenuBar(menuBar);

		getSaveBtn().setIcon(saveIcon);
		addBtn.setIcon(addIcon);
		deleteBtn.setIcon(deleteIcon);
		toolBar.add(getSaveBtn());
		toolBar.add(addBtn);
		toolBar.add(deleteBtn);
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.SOUTH);

		getNoms().addMouseListener(new PopUpListener(this));
		saveMenuItem.addActionListener(new Enregistrer(this, contactsController));
		addMenuItem.addActionListener(new Ajouter(this, contactsController));
		deletePopupItem.addActionListener(new Supprimer(this, contactsController));
		deleteMenuItem.addActionListener(new Supprimer(this, contactsController));
		getSaveBtn().addActionListener(new Enregistrer(this, contactsController));
		addBtn.addActionListener(new Ajouter(this, contactsController));
		deleteBtn.addActionListener(new Supprimer(this, contactsController));
		infosContacts.getDocument().addDocumentListener(new InfoDocumentListener(this, contactsController));
		getNoms().addListSelectionListener(new ContactsListListenener(this, contactsController));
		this.addWindowListener(new WindowsListener(this, contactsController));
		
		String language = System.getProperty("user.language");
		String country = System.getProperty("user.country");
		Locale currentLocale = new Locale(language,country);
		setAllTitle(currentLocale);
		
		contactsController.setChange(false);
		getSaveBtn().setEnabled(false);
	}

	public void setListData() {
		contactsController.setList();
		contactsController.setEnable(getSaveBtn());
		getNoms().setListData(contactsController.getListNoms());
	}

	public String getInfosText() {
		return infosContacts.getText();
	}

	public void setInfosText(String infosText) {
		infosContacts.setText(infosText);
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(JButton saveBtn) {
		this.saveBtn = saveBtn;
	}

	public JList<String> getNoms() {
		return jListNoms;
	}

	public JPopupMenu getPopUpMenu() {
		return popUpMenu;
	}

	public void setPopUpMenu(JPopupMenu popUpMenu) {
		this.popUpMenu = popUpMenu;
	}
	
	private void setAllTitle(Locale locale) {
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
	}
	
	public String getStringFromBundle(String key) {
		return messages.getString(key);
	}
	
	public static void main(String[] args) {
		AdresseBook book = new AdresseBook();
		book.init();
		book.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		book.setSize(400, 400);
		book.setLocation(200, 200);
		book.setVisible(true);
		
	}
}
