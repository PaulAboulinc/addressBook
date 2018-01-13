package View;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ContactsListListenener implements ListSelectionListener{

	AdresseBook book;
	
	public ContactsListListenener(AdresseBook b) {
		book = b;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList item = (JList) e.getSource();
		if (!e.getValueIsAdjusting()) {
			for (Object s : book.getContacts().keySet()) {
				if (s.equals(item.getSelectedValue())) {
					book.nom = (String) s;
					book.infos.setText(book.getContacts().getProperty(book.nom));
				}
			}
		}
	}

}
