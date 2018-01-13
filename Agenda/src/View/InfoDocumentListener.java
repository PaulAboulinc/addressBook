package View;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class InfoDocumentListener implements DocumentListener {

	AdresseBook book;
	
	public InfoDocumentListener(AdresseBook b) {
		book = b;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		book.modifierContact();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		book.modifierContact();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		book.modifierContact();
	}

}
