package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.ContactsController;
import Model.ContactsManager;

public class AdresseBook extends JFrame {

	private ContactsController contactsController = new ContactsController();
	
	String nom;
	JList<String> noms = new JList<String>(getListNoms());
	JScrollPane ascenseur = new JScrollPane(noms);
	JTextPane infos = new JTextPane();
	JMenu fileMenu = new JMenu("Fichier");
	JMenu contactsMenu = new JMenu("Contacts");
	JMenuItem saveMenuItem;
	JMenuItem addMenuItem;
	JMenuItem deleteMenuItem;
	JMenuBar menuBar = new JMenuBar();
	JToolBar tb = new JToolBar("Toolbar");
	JButton addBtn = new JButton("Ajouter");
	JButton saveBtn = new JButton("Enregistrer");
	JButton deleteBtn = new JButton("Supprimer");
	JPopupMenu popUpMenu = new JPopupMenu("click !");
	
	public void init() {
		this.addWindowListener(new WindowsListener(this));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("AdresseBook");
		this.setSize(400, 400);
		this.setLocation(200, 200);
		
		
		popUpMenu.addMouseListener(new PopUpListener(this));
		noms.setComponentPopupMenu(popUpMenu);
		
		String saveImagePath = "lib/toolbarButtonGraphics/general/Save16.gif";
		ImageIcon saveIcon = new ImageIcon(saveImagePath);
		
		String addImagePath = "lib/toolbarButtonGraphics/general/Add16.gif";
		ImageIcon addIcon = new ImageIcon(addImagePath);
		
		String deleteImagePath = "lib/toolbarButtonGraphics/general/Delete16.gif";
		ImageIcon deleteIcon = new ImageIcon(deleteImagePath);		
		
		saveMenuItem = new JMenuItem("Enregistrer", saveIcon);
		addMenuItem = new JMenuItem("Ajouter", addIcon);
		deleteMenuItem = new JMenuItem("Supprimer", deleteIcon);
		
		setLayout(new BorderLayout());
		saveMenuItem.addActionListener(new Enregistrer(this));
		addMenuItem.addActionListener(new Ajouter(this));
		deleteMenuItem.addActionListener(new Supprimer(this));
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,
				ActionEvent.CTRL_MASK));
		fileMenu.add(saveMenuItem);
		contactsMenu.add(addMenuItem);
		contactsMenu.add(deleteMenuItem);
		
		popUpMenu.add(deleteMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(contactsMenu);

		add(ascenseur, BorderLayout.NORTH);
		add(infos);
		setJMenuBar(menuBar);

		saveBtn.setIcon(saveIcon);
		addBtn.setIcon(addIcon);
		deleteBtn.setIcon(deleteIcon);
		saveBtn.addActionListener(new Enregistrer(this));
		tb.add(saveBtn);
		addBtn.addActionListener(new Ajouter(this));
		tb.add(addBtn);
		deleteBtn.addActionListener(new Supprimer(this));
		tb.add(deleteBtn);
		tb.setFloatable(false);
		add(tb, BorderLayout.SOUTH);

		this.setVisible(true);

		infos.getDocument().addDocumentListener(new InfoDocumentListener(this));
		noms.addListSelectionListener(new ContactsListListenener(this));
		contactsController.setChange(false);
		saveBtn.setEnabled(false);
	}

	public void chargerContacts() {
		contactsController.chargerContacts();
	}

	public void setList() {
		contactsController.setList();
		noms.setListData(contactsController.getListNoms());
		saveBtn.setEnabled(getChange());
	}

	public void modifierP(Properties p, String key, String value) {
		contactsController.modifierP(p, key, value);
	}
	
	public void modifierContact() {
		String value = infos.getText();
		String key = nom;
		contactsController.modifierContact(value, key);
		setList();
	}

	public void ajouterP(Properties p, String key, String value) {
		contactsController.ajouterP(p, key, value);
		setList();
	}

	public void supprimerP(Properties p, String key) {
		contactsController.supprimerP(p, key);
	}

	public void enregistrer() {
		contactsController.enregistrer();
		saveBtn.setEnabled(getChange());
	}
	
	public boolean getChange () {
		return contactsController.getChange();
	}
	
	public void setChange (boolean newChange) {
		contactsController.setChange(newChange);
	}
	
	public Properties getContacts () {
		return contactsController.getContacts();
	}
	
	public Vector<String> getListNoms() {
		return contactsController.getListNoms();
	}
	
	public static void main(String[] args) {
		AdresseBook book = new AdresseBook();
		book.init();

	}
}
