package Affichage;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class Logs extends JPanel {

	private JTextArea textArea = new JTextArea(""); 
	
	/**
	 * Create the panel.
	 */
	public Logs() {
		setLayout(new BorderLayout(1, 1));

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setEnabled(true);
		add(scrollPane, BorderLayout.CENTER);
		textArea.setEditable(false);

//		textArea.setWrapStyleWord(true);
//		textArea.setLineWrap(true);

	}

	public void ajouterLog(String log) {
		System.out.println(log);
		textArea.setText(textArea.getText() + "\n" + log);
		textArea.repaint();
		this.repaint();
		
	}
	
}
