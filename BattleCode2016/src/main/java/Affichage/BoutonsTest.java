package Affichage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BoutonsTest extends JPanel {

	/**
	 * Create the panel.
	 */
	public BoutonsTest() {
		
		GestionEvenements gestionEvenements = new GestionEvenements();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{84, 39, 119, 83, 0};
		gridBagLayout.rowHeights = new int[] {50, -3, 125, 98, 40};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnPing = new JButton("Ping");
		gestionEvenements.ajouterEvenementPing(btnPing);
		GridBagConstraints gbc_btnPing = new GridBagConstraints();
		gbc_btnPing.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnPing.insets = new Insets(0, 0, 5, 5);
		gbc_btnPing.gridx = 0;
		gbc_btnPing.gridy = 0;
		add(btnPing, gbc_btnPing);
		
		JButton btnPing_1 = new JButton("Ping500");
		gestionEvenements.ajouterEvenementPing500(btnPing_1);
		GridBagConstraints gbc_btnPing_1 = new GridBagConstraints();
		gbc_btnPing_1.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnPing_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnPing_1.gridx = 1;
		gbc_btnPing_1.gridy = 0;
		add(btnPing_1, gbc_btnPing_1);
		
		JButton btnPing_2 = new JButton("Ping403");
		gestionEvenements.ajouterEvenementPing403(btnPing_2);
		GridBagConstraints gbc_btnPing_2 = new GridBagConstraints();
		gbc_btnPing_2.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnPing_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnPing_2.gridx = 2;
		gbc_btnPing_2.gridy = 0;
		add(btnPing_2, gbc_btnPing_2);
		
		JButton btnGetidequipe = new JButton("GetIdEquipe");
		gestionEvenements.ajouterEvenementGetIdEquipe(btnGetidequipe);
		GridBagConstraints gbc_btnGetidequipe = new GridBagConstraints();
		gbc_btnGetidequipe.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnGetidequipe.insets = new Insets(0, 0, 5, 5);
		gbc_btnGetidequipe.gridx = 0;
		gbc_btnGetidequipe.gridy = 1;
		add(btnGetidequipe, gbc_btnGetidequipe);
		
		JButton btnStatus = new JButton("Status");
		gestionEvenements.ajouterEvenementStatus(btnStatus);
		GridBagConstraints gbc_btnStatus = new GridBagConstraints();
		gbc_btnStatus.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnStatus.insets = new Insets(0, 0, 5, 5);
		gbc_btnStatus.gridx = 1;
		gbc_btnStatus.gridy = 1;
		add(btnStatus, gbc_btnStatus);
		
		JButton btnBoard = new JButton("Board");
		gestionEvenements.ajouterEvenementBoard(btnBoard);
		GridBagConstraints gbc_btnBoard = new GridBagConstraints();
		gbc_btnBoard.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnBoard.insets = new Insets(0, 0, 5, 5);
		gbc_btnBoard.gridx = 2;
		gbc_btnBoard.gridy = 1;
		add(btnBoard, gbc_btnBoard);
		
		JButton btnLastmove = new JButton("LastMove");
		gestionEvenements.ajouterEvenementLastMove(btnLastmove);
		GridBagConstraints gbc_btnLastmove = new GridBagConstraints();
		gbc_btnLastmove.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnLastmove.insets = new Insets(0, 0, 5, 0);
		gbc_btnLastmove.gridx = 3;
		gbc_btnLastmove.gridy = 1;
		add(btnLastmove, gbc_btnLastmove);
		
		JButton btnGo = new JButton("Go Bot!");
		gestionEvenements.ajouterEvenementGoBot(btnGo);
		GridBagConstraints gbc_btnGo = new GridBagConstraints();
		gbc_btnGo.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnGo.insets = new Insets(0, 0, 0, 5);
		gbc_btnGo.gridx = 1;
		gbc_btnGo.gridy = 3;
		add(btnGo, gbc_btnGo);
		
		JButton btnGoVersus = new JButton("Go Versus !");
		gestionEvenements.ajouterEvenementGoVersus(btnGoVersus);
		GridBagConstraints gbc_btnGoVersus = new GridBagConstraints();
		gbc_btnGoVersus.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnGoVersus.insets = new Insets(0, 0, 0, 5);
		gbc_btnGoVersus.gridx = 2;
		gbc_btnGoVersus.gridy = 3;
		add(btnGoVersus, gbc_btnGoVersus);

	}

}
