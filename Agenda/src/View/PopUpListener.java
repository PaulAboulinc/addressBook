package View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PopUpListener extends MouseAdapter {
	
	AdresseBook book;
	
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
        	book.noms.setSelectedIndex(book.noms.locationToIndex(e.getPoint())); 
        	book.popUpMenu.show(book.noms, e.getX(), e.getY()); //and show the menu
        }
    }
}