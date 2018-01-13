package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Enregistrer implements ActionListener{
	
	AdresseBook book;
	
	public Enregistrer(AdresseBook b) {
		book=b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		book.enregistrer();
	}
}
