package Affichage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import transverse.Constantes;

public class Principale extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Principale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Accueil", new BoutonsTest());
		tabbedPane.addTab("Logs", Constantes.logs = new Logs());
		tabbedPane.addTab("Plateau", Constantes.plateauIHM = new Plateau());
		tabbedPane.setSelectedIndex(0);
	}

}
