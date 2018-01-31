package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.AdresseBook;

/**
 * La classe PopUpListener écoute le menu contextuel.
 * 
 * @author ABOULINC
 *
 */
public class PopUpListener extends MouseAdapter {
	
	/**
	 * Instance de la fenetre principale.
	 */
	private AdresseBook book;
	
	/**
	 * Constructeur de la classe PopUpListener
	 * @param b Instance d'AdresseBook 
	 */
    public PopUpListener(AdresseBook b) {
		book = b;
	}
    
    /**
     * Appelle la fonction doPop en lui passant en paramètre le MouseEvent.
     */
	public void mousePressed(MouseEvent e){
            doPop(e);
    }
	
	/**
     * Appelle la fonction doPop en lui passant en paramètre le MouseEvent.
     */
    public void mouseReleased(MouseEvent e){
            doPop(e);
    }

    /**
     * Selectionne le contact et affiche le menu contextuel.
     * @param e
     */
    private void doPop(MouseEvent e){
        if (e.isPopupTrigger()) {
        	book.getNoms().setSelectedIndex(book.getNoms().locationToIndex(e.getPoint())); 
        	book.getPopUpMenu().show(book.getNoms(), e.getX(), e.getY());
        }
    }
}