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
import Model.Ajouter;
import Model.ContactsListListenener;
import Model.Enregistrer;
import Model.InfoDocumentListener;
import Model.PopUpListener;
import Model.Supprimer;
import Model.WindowsListener;

public class AdresseBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContactsController contactsController = new ContactsController();
	
	public JList<String> noms = new JList<String>(contactsController.getListNoms());
	private JScrollPane contactsScrollPane = new JScrollPane(noms);
	public JTextPane infos = new JTextPane();
	private JScrollPane infosScrollPane = new JScrollPane(infos);
	private JPanel adresseBook = new JPanel();
	private JMenu fileMenu = new JMenu("Fichier");
	private JMenu contactsMenu = new JMenu("Contacts");
	private JMenuItem saveMenuItem;
	private JMenuItem addMenuItem;
	private JMenuItem deleteMenuItem;
	private JMenuItem deletePopupItem;
	private JMenuBar menuBar = new JMenuBar();
	private JToolBar toolBar = new JToolBar("Toolbar");
	private JButton addBtn = new JButton("Ajouter");
	private JButton deleteBtn = new JButton("Supprimer");
	public JButton saveBtn = new JButton("Enregistrer");
	public JPopupMenu popUpMenu = new JPopupMenu("click !");
	
	public void init() {
				
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
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,ActionEvent.CTRL_MASK));
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
		//this.addWindowListener(new WindowsListener(this, contactsController));
		this.addWindowListener(new WindowsListener(this, contactsController));
		
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
		book.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		book.setTitle("AdresseBook");
		book.setSize(400, 400);
		book.setLocation(200, 200);
		book.setVisible(true);

	}
}
