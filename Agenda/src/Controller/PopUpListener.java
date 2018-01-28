package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.AdresseBook;

public class PopUpListener extends MouseAdapter {
	
	private AdresseBook book;
	
    public PopUpListener(AdresseBook adresseBook) {
		book = adresseBook;
	}

	public void mousePressed(MouseEvent e){
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
            doPop(e);
    }

    private void doPop(MouseEvent e){
        if (e.isPopupTrigger()) {
        	book.getNoms().setSelectedIndex(book.getNoms().locationToIndex(e.getPoint())); 
        	book.getPopUpMenu().show(book.getNoms(), e.getX(), e.getY()); //and show the menu
        }
    }
}