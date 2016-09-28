package Affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import metier.Traitements;

public class GestionEvenements implements ActionListener {

	JButton ping, ping500, ping403, idEquipe, status, board, lastMove, goBot, goVersus;
	
	public void ajouterEvenementPing(JButton bouton) {
		bouton.addActionListener(this);
		ping = bouton;
	}

	public void ajouterEvenementPing500(JButton bouton) {
		bouton.addActionListener(this);
		ping500 = bouton;
	}

	public void ajouterEvenementPing403(JButton bouton) {
		bouton.addActionListener(this);
		ping403 = bouton;
	}

	public void ajouterEvenementGetIdEquipe(JButton bouton) {
		bouton.addActionListener(this);
		idEquipe = bouton;
	}

	public void ajouterEvenementStatus(JButton bouton) {
		bouton.addActionListener(this);
		status = bouton;
	}

	public void ajouterEvenementBoard(JButton bouton) {
		bouton.addActionListener(this);
		board = bouton;
	}
	

	public void ajouterEvenementLastMove(JButton bouton) {
		bouton.addActionListener(this);
		lastMove = bouton;
	}
	

	public void ajouterEvenementGoBot(JButton bouton) {
		bouton.addActionListener(this);
		goBot = bouton;
	}
	
	public void ajouterEvenementGoVersus(JButton bouton) {
		bouton.addActionListener(this);
		goVersus = bouton;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ping) {
			Traitements.traiterPing();
		} else if (e.getSource() == ping500) {
			Traitements.traiterPing500();
		} else if (e.getSource() == ping403) {
			Traitements.traiterPing500();
		} else if (e.getSource() == idEquipe) {
			Traitements.traiterGetIdEquipe();
		} else if (e.getSource() == board) {
			Traitements.traiterBoard();
		} else if (e.getSource() == lastMove) {
			Traitements.traiterLastMove();
		} else if (e.getSource() == status) {
			Traitements.traiterStatus();
		} else if (e.getSource() == goBot) {
			Traitements.traiterGoBot();
		} else if (e.getSource() == goVersus) {
			Traitements.traiterGoVersus();
		}
	}

}
