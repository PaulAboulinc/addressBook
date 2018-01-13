package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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


import Controller.ContactsController;

public class AdresseBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContactsController contactsController = new ContactsController();
	
	JList<String> noms = new JList<String>(contactsController.getListNoms());
	JScrollPane contactsScrollPane = new JScrollPane(noms);
	JTextPane infos = new JTextPane();
	JScrollPane infosScrollPane = new JScrollPane(infos);
	JPanel adresseBook = new JPanel();
	JMenu fileMenu = new JMenu("Fichier");
	JMenu contactsMenu = new JMenu("Contacts");
	JMenuItem saveMenuItem;
	JMenuItem addMenuItem;
	JMenuItem deleteMenuItem;
	JMenuItem deletePopupItem;
	JMenuBar menuBar = new JMenuBar();
	JToolBar toolBar = new JToolBar("Toolbar");
	JButton addBtn = new JButton("Ajouter");
	JButton saveBtn = new JButton("Enregistrer");
	JButton deleteBtn = new JButton("Supprimer");
	JPopupMenu popUpMenu = new JPopupMenu("click !");
	
	public void init() {
		//todo : déplacer dans le main 
		this.addWindowListener(new WindowsListener(this, contactsController));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("AdresseBook");
		this.setSize(400, 400);
		this.setLocation(200, 200);
				
		String saveImagePath = "lib/toolbarButtonGraphics/general/Save16.gif";
		String addImagePath = "lib/toolbarButtonGraphics/general/Add16.gif";
		String deleteImagePath = "lib/toolbarButtonGraphics/general/Delete16.gif";
		
		ImageIcon saveIcon = new ImageIcon(saveImagePath);
		ImageIcon addIcon = new ImageIcon(addImagePath);
		ImageIcon deleteIcon = new ImageIcon(deleteImagePath);				
		
		saveMenuItem = new JMenuItem("Enregistrer", saveIcon);
		addMenuItem = new JMenuItem("Ajouter", addIcon);
		deleteMenuItem = new JMenuItem("Supprimer", deleteIcon);
		deletePopupItem = new JMenuItem("Supprimer", deleteIcon);
		
		setLayout(new BorderLayout());
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.CTRL_MASK));
		fileMenu.add(saveMenuItem);
		contactsMenu.add(addMenuItem);
		contactsMenu.add(deleteMenuItem);
		
		popUpMenu.add(deletePopupItem);
		menuBar.add(fileMenu);
		menuBar.add(contactsMenu);
		
		adresseBook.setLayout(new BorderLayout());
		adresseBook.add(contactsScrollPane);
		infosScrollPane.setPreferredSize(new Dimension(600, 50));
		adresseBook.add(infosScrollPane, BorderLayout.SOUTH);
		add(adresseBook);
		setJMenuBar(menuBar);

		saveBtn.setIcon(saveIcon);
		addBtn.setIcon(addIcon);
		deleteBtn.setIcon(deleteIcon);
		toolBar.add(saveBtn);
		toolBar.add(addBtn);
		toolBar.add(deleteBtn);
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.SOUTH);

		this.setVisible(true);

		noms.addMouseListener(new PopUpListener(this));
		saveMenuItem.addActionListener(new Enregistrer(this, contactsController));
		addMenuItem.addActionListener(new Ajouter(this, contactsController));
		deletePopupItem.addActionListener(new Supprimer(this, contactsController));
		deleteMenuItem.addActionListener(new Supprimer(this, contactsController));
		saveBtn.addActionListener(new Enregistrer(this, contactsController));
		addBtn.addActionListener(new Ajouter(this, contactsController));
		deleteBtn.addActionListener(new Supprimer(this, contactsController));
		infos.getDocument().addDocumentListener(new InfoDocumentListener(this, contactsController));
		noms.addListSelectionListener(new ContactsListListenener(this, contactsController));
		
		contactsController.setChange(false);
		saveBtn.setEnabled(false);
	}

	public void setListData() {
		contactsController.setList();
		contactsController.setEnable(saveBtn);
		noms.setListData(contactsController.getListNoms());
	}
	
	public static void main(String[] args) {
		AdresseBook book = new AdresseBook();
		book.init();

	}
}
