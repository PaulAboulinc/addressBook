package View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PopUpListener extends MouseAdapter {
	
	AdresseBook book;
	
    public PopUpListener(AdresseBook adresseBook) {
		book = adresseBook;
	}

	public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        book.popUpMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}