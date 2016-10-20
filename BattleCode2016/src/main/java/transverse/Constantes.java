package transverse;

import java.util.HashMap;
import java.util.Map;

import Affichage.Logs;
import Affichage.Plateau;

public class Constantes {

	// Erreur
	/**
	 * NA = Erreur 406
	 */
	public static String NA = "NA";
	
	// Retour
	public static String OUI = "CANPLAY";
	public static String NON = "CANTPLAY";
	public static String ANNULE = "CANCELLED";
	public static String GAGNE = "VICTORY";
	public static String PERDU = "DEFEAT";
	public static String GAMEOVER = "GAMEOVER";
	

	public static String OK = "OK";
	public static String KO = "KO";
	
    // public static String URL = "http://www.codeandplay.date/test-ws/";
//    public static String URL = "http://www.codeandplay.date/battle-ws/duel/";
    public static String URL = "http://evenement.codeandplay.date//epic-ws/epic/";
	
	// PING
	public static String PING = "ping";

	public static String PING500 = "pingError500";

	public static String PING403 = "pingError403";
	
	
	// Player
    // public static String NOM_EQUIPE = "Team-1991-Sauf-Maxime-Qui-Est-De-1988";
    // public static String MDP = "FA188391ahah";
    // public static String GETID = "player/getIdEquipe/" + NOM_EQUIPE + "/" + MDP;
//    public static String NOM_EQUIPE = "aaa-Team-Des-Jeremy-Lol";
//
//    public static String MDP = "IlsSontChiantsSurSim@tAvecLeurNomDEquipeQuiFait10kmDeLong";
//
//	public static String GETID = "player/getIdEquipe/" + NOM_EQUIPE + "/" + MDP;
//	
    public static String NOM_EQUIPE = "aaa-Team-Des-JerOnDoitAbregerEnFaitDsl";

    public static String MDP = "Cyr1l-3st_FonctionNel";

	public static String GETID = "player/getIdEquipe/" + NOM_EQUIPE + "/" + MDP;
	
	/**
	 *  Affrontement joueur
	 *  versus/next/{idEquipe}
	 */
	public static String NEXT_JOUEUR = "versus/next/";
	
	
	/**
	 * Affrontement bot
	 * practice/new/{level}/{idEquipe}
	 */
	public static String NEW_BOT = "practice/new/";
	/**
	 * practice/next/{idEquipe}
	 */
	public static String NEXT_BOT = "practice/next/";
	
	
	//Partie
	/**
	 * game/status/{idPartie}/{idEquipe}
	 */
	public static String STATUS = "game/status/";
	/**
	 * game/board/{idPartie}
	 */
	public static String BOARD = "game/board/";
	/**
	 * game/getlastmove/{idPartie}
	 */
	public static String LAST_MOVE = "game/getlastmove/";
	/**
	 * game/play/{idPartie}/{idEquipe}/{coordX}/{coordY}
	 */
	public static String PLAY = "game/play/";
	
	
	public static Map<String, String> WORKFLOW_PRACTICE;
	static
    {
		WORKFLOW_PRACTICE = new HashMap<String, String>();
		WORKFLOW_PRACTICE.put(GETID, NEW_BOT);
		WORKFLOW_PRACTICE.put(NEW_BOT, NEXT_BOT);
		WORKFLOW_PRACTICE.put(NEXT_BOT, STATUS);
		WORKFLOW_PRACTICE.put(STATUS, BOARD);
		WORKFLOW_PRACTICE.put(BOARD, PLAY);
		WORKFLOW_PRACTICE.put(PLAY, STATUS);
    }

	public static Map<String, String> WORKFLOW_PLAYER;
	static
    {
		WORKFLOW_PRACTICE = new HashMap<String, String>();
		WORKFLOW_PRACTICE.put(GETID, NEXT_JOUEUR);
		WORKFLOW_PRACTICE.put(NEXT_JOUEUR, STATUS);
		WORKFLOW_PRACTICE.put(STATUS, BOARD);
		WORKFLOW_PRACTICE.put(BOARD, PLAY);
		WORKFLOW_PRACTICE.put(PLAY, STATUS);
    }
	
	public static final boolean TEST = true;

    public static final boolean VIA_PROXY = false;
	
	// Bien dégueu
	public static Logs logs;
	public static Plateau plateauIHM;
	
	
	// Mouvement
	public static String SHOOT = "SHOOT";
	public static String RELOAD = "RELOAD";
	public static String COVER = "COVER";
	public static String AIM  = "AIM";
    public static String BOMB = "BOMB";
    
    // Nombre de niveaux
    public static int NB_LEVEL = 7;
    
    // Combobox default
    public static String IA_DEFAULT = "SER";
}
