package Affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Plateau extends JPanel {

	/**
	 * Create the panel.
	 */
	public Plateau() {

	}

	public void genererPlateau(int longueur, int largeur) {

		this.removeAll();
		
		setLayout(new GridLayout(longueur, largeur, 0, 0));

		for (int row = 0; row < longueur; row++)
			for (int col = 0; col < largeur; col++) {
				boolean white = (col % 2 == 0) == (row % 2 == 0);
				add(new DrawRect(white ? "WHITE" : "BLACK"));
			}
			

	}
	

}

class DrawRect extends JPanel {
	private String ngjyra = "BLACK";

	public DrawRect(String b) {
		ngjyra = b;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (ngjyra.equals("BLACK"))
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		// add the square with the specified color

	}
}
