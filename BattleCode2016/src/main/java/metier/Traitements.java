package metier;

import com.google.gson.Gson;
import connecteur.Connecteur;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import transverse.Constantes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Traitements {

    private static String idEquipe = null;

    private static String idPartie = null;



    private static String lastShoot = null;



    public static void traiterPing() {
        appeler(Constantes.PING, null);
    }

    public static void traiterPing500() {
        appeler(Constantes.PING500, null);
    }

    public static void traiterPing403() {
        appeler(Constantes.PING403, null);
    }

    // game/getIdEquipe/{nomEquipe}/{MotDePasse} (argument déjà présents dans la constante)
    public static void traiterGetIdEquipe() {
        idEquipe = appeler(Constantes.GETID, null);
    }

    // game/status/{idPartie}/{idEquipe}
    public static void traiterStatus() {
        if (idEquipe == null) {
            traiterGetIdEquipe();
        }
        appeler(Constantes.STATUS, null);
    }

    // game/board/{idPartie}
    public static void traiterBoard() {
        if (idEquipe == null) {
            traiterGetIdEquipe();
        }
        appeler(Constantes.BOARD, null);
    }

    // game/getlastmove/{idPartie}
    public static void traiterLastMove() {
        if (idEquipe == null) {
            traiterGetIdEquipe();
        }
        appeler(Constantes.LAST_MOVE, null);
    }

    // game/getlastmove/{idPartie}
	public static void traiterGoBot(String lvlBot, String ia) {

		if (!lvlBot.equals("ALL")) {
			Constantes.logs.ajouterLog("\n");
			traiterGetIdEquipe();

			idPartie = Constantes.NA;
			while (Constantes.NA.equals(idPartie)) {

				Object selected = idPartie = appeler(Constantes.NEW_BOT, Arrays.asList(lvlBot, idEquipe));

			}

			String status = traitementJeu(ia);

			if (Constantes.GAGNE.equals(status)) {
				Constantes.logs.ajouterLog("==== VICTOIRE DE LA TEAM !! ====");
			} else if (Constantes.PERDU.equals(status)) {
				Constantes.logs.ajouterLog("==== MALHEUREUSE DEFAITE... ====");
			}
		} else {
			for (int i = 1; i <= Constantes.NB_LEVEL; i++) {
				lvlBot = String.valueOf(i);

				Constantes.logs.ajouterLog("\n");
				traiterGetIdEquipe();

				idPartie = Constantes.NA;
				while (Constantes.NA.equals(idPartie)) {

					Object selected = idPartie = appeler(Constantes.NEW_BOT, Arrays.asList(lvlBot, idEquipe));

				}

				String status = traitementJeu(ia);

				if (Constantes.GAGNE.equals(status)) {
					Constantes.logs.ajouterLog("==== VICTOIRE DE LA TEAM !! ====");
				} else if (Constantes.PERDU.equals(status)) {
					Constantes.logs.ajouterLog("==== MALHEUREUSE DEFAITE... ====");
				}
			}

		}

	}

    // game/getlastmove/{idPartie}
    public static void traiterGoVersus(String ia) {
        Constantes.logs.ajouterLog("\n");
        traiterGetIdEquipe();

        idPartie = Constantes.NA;
        while (Constantes.NA.equals(idPartie)) {
            idPartie = appeler(Constantes.NEXT_JOUEUR, Arrays.asList(idEquipe));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String status = traitementJeu(ia);

        if (Constantes.GAGNE.equals(status)) {
            Constantes.logs.ajouterLog("==== VICTOIRE DE LA TEAM !! ===");
        } else if (Constantes.PERDU.equals(status)) {
            Constantes.logs.ajouterLog("=== MALHEUREUSE DEFAITE... ===");
        }

    }

    private static String traitementJeu(String ia) {
        String status = Constantes.NON;
        String lastMove = null;
        while (!Constantes.GAMEOVER.equals(status) && !Constantes.GAGNE.equals(status)
                && !Constantes.PERDU.equals(status)) {
            while (Constantes.NON.equals(status) || Constantes.ANNULE.equals(status)) {
                status = appeler(Constantes.STATUS, Arrays.asList(idPartie, idEquipe));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (Constantes.OUI.equals(status)) {
                Board plateau = extraitJson(appeler(Constantes.BOARD, Arrays.asList(idPartie)));

                lastMove = ia(lastMove, plateau, ia);

                String retour = appeler(Constantes.PLAY, Arrays.asList(idPartie, idEquipe, lastMove));

                // Mauvais Coup
                if (Constantes.KO.equals(retour)) {
                    status = Constantes.PERDU;
                } else if (Constantes.GAMEOVER.equals(retour)) {
                	lastShoot = null;
                	status = appeler(Constantes.STATUS, Arrays.asList(idPartie, idEquipe));
                }

            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // if (Constantes.TEST) {
            // status = Constantes.PERDU;
            // }

        }
        return status;
    }

	private static String ia(String lastMove, Board plateau, String ia) {
//		if (ia.equals("SER")) {
//			return iaSER(lastMove, plateau);
//		} else if (ia.equals("SAY")) {
//			return iaSAY(lastMove, plateau);
//		} else if (ia.equals("JLN")) {
//			return iaJLN(lastMove, plateau);
//		} else if (ia.equals("JLL")) {
//			return iaJLL(lastMove, plateau);
//		}

		// IncohÈrent
		return iaJLN(lastMove, plateau);
	}


    private static String iaJLN(String lastMove, Board plateau) {
        String dernierMouvement = appeler(Constantes.LAST_MOVE, Arrays.asList(idPartie, idEquipe));

        Constantes.logs.ajouterLog(plateau.toString());
        // Traitement metier

        Player nous = null;
        Player eux = null;
        
        List<Player> players =  plateau.getPlayerBoards();
        for (Player player : players) {
			if(player.getPlayerName().equals("aaa-Team-Des-JerOnDoitAbregerEnFaitDsl")){
				nous = player;
			}else{
				eux = player;
			}
		}

        String mouvement = "";
        
        if(plateau.getNbrTurnsLeft() == 53){
        	mouvement = "CHAMAN";
        }else if(plateau.getNbrTurnsLeft() == 52){
        	mouvement = "PALADIN";
        }else if(plateau.getNbrTurnsLeft() == 51){
        	mouvement = "ARCHER";
        }else{
        	String coup1 = "A1,ATTACK,";
        	String coup2 = "A2,ATTACK,";
        	String coup3 = "A3,ATTACK,";
        	
        	Fighter archer = possede("ARCHER",nous);
        	Fighter chaman = possede("CHAMAN",nous);
        	Fighter paladin = possede("PALADIN",nous);
        	
        	if(plateau.getNbrTurnsLeft() == 50){
        		// PREMIER TOUR
            	coup1 = "A1,REST,A1";
            	coup2 = "A2,ATTACK,E1";
            	coup3 = "A3,REST,A3";
        	}else{
        		coup1 += getWeakerFigther(eux);
        		coup3 += getWeakerFigther(eux);
        		Fighter f = possede("CHAMAN", eux);
        		if(f!=null){
        			if(f.getCurrentMana()>=2){
        				coup2 = "A2,REST,A2";
        				if(paladin.getCurrentMana()>=2){
                			coup2 = "A2,CHARGE,"+getWeakerFigther(eux);
            			}
        			}else{
        				// CHAMAN ADVERSE SANS AP
                    	coup2 = "A2,ATTACK,"+getWeakerFigther(eux);
                    	if(archer.getCurrentMana()>=2){
                			coup3 = "A3,FIREBOLT,"+getWeakerFigther(eux);
            			}
        			}
        		}else{
        			// ILS ONT PAS DE CHAMAN
        			coup2 = "A2,ATTACK,"+getWeakerFigther(eux);
        			if(archer.getCurrentMana()>=2){
            			coup3 = "A3,FIREBOLT,"+getWeakerFigther(eux);
        			}
        		}
        		
        		String brule = getFighterBrule(nous);
        		if(brule!= null){
        			if(chaman.getCurrentMana()>=2){
        				coup1="A1,CLEANSE,"+brule;
        			}
        		}
        		
        		if(chaman.getCurrentMana()<2){
        			coup1="A1,REST,A1";
        		}
        		
        		String effraye = getFighterEffraye(nous);
        		if(effraye!= null){
    				if("A1".equals(effraye)){
    					coup1=effraye+",DEFEND,"+effraye;
    				}else if("A2".equals(effraye)){
    					coup2=effraye+",DEFEND,"+effraye;
    				}else{
    					coup3=effraye+",DEFEND,"+effraye;
    				}
        		}
        		
        	}
        	
        	
        	mouvement =coup1+"$"+coup2+"$"+coup3;
        }
        return mouvement;
    }
    
    
    public static String getFighterBrule(Player p) {

        for(int i = 0; i < p.getFighters().size(); i++) {
            if(null != p.getFighters().get(i).getStates() && p.getFighters().get(i).getStates().contains("BURNING")) {
                return "A"+(i+1);
            }
        }

        return null;
    }

    public static String getFighterEffraye(Player p) {

        for(int i = 0; i < p.getFighters().size(); i++) {
            if(null != p.getFighters().get(i).getStates() && p.getFighters().get(i).getStates().contains("SCARED")) {
                return "A"+(i+1);
            }
        }

        return null;
    }

    public static String getFighterEtourdi(Player p) {

        for( int i = 0; i < p.getFighters().size(); i++) {
            if(null != p.getFighters().get(i).getStates() && p.getFighters().get(i).getStates().contains("STUNNED")) {
                return "A"+(i+1);
            }
        }

        return null;
    }
    
    public static String getWeakerFigther(Player p) {

    	Fighter f = possede("PRIEST",p);
    	Fighter f2 = possede("CHAMAN",p);

    	if(f!=null){
    		return "E"+f.getOrderNumberInTeam();
    	}
    	if(f2!=null){
    		return "E"+f2.getOrderNumberInTeam();
    	}
        int minLife = 1000;
        int weaker = 1;
        for(int i = 0; i < p.getFighters().size(); i++) {
            if(p.getFighters().get(i).getCurrentLife()>0 && p.getFighters().get(i).getCurrentLife() < minLife) {
                minLife = p.getFighters().get(i).getCurrentLife();
                weaker = i+1;
            }
        }

        return "E"+weaker;
    }
    
    
    public static  Fighter possede(String s, Player p){
    	for(Fighter f : p.getFighters()){
    		if(f.getFighterClass().equals(s)){
    			return f;
    		}
    	}
    	return null;
    }
    
    private static String iaSER(String lastMove, Board plateau) {
        return null;
    }

    private static String iaJLL(String lastMove, Board plateau) {
        return Constantes.SHOOT;
    }

    private static String iaSAY(String lastMove, Board plateau) {
    	String dernierMouvement = appeler(Constantes.LAST_MOVE, Arrays.asList(idPartie, idEquipe));

        Constantes.logs.ajouterLog(plateau.toString());
        // Traitement metier

        // test soizic
        Player nous = null;
        Player eux = null;

        if (Constantes.NOM_EQUIPE.equals(plateau.getPlayer1().getPlayerName())) {
            nous = plateau.getPlayer1();
            eux = plateau.getPlayer2();
        } else {
            nous = plateau.getPlayer2();
            eux = plateau.getPlayer1();
        }

//        int notreNbBalles = nous.getBullet();
//        int notreNbBouclie = nous.getShield();
//        int notreNbVie = nous.getHealth();
//        int notreNbBombe = nous.getBomb();
//
//        int nbBallesAdverse = eux.getBullet();
//        int nbBouclieAdverse = eux.getShield();
//        int nbVieAdverse = eux.getHealth();
//        int nbBombeAdverse = eux.getBomb();

        String mouvement = Constantes.SHOOT;

    	return mouvement;
    }

    private static Board extraitJson(String json) {
        Gson gson = new Gson();
        
        return gson.fromJson(json, Board.class);
    }

    private static String appeler(String url, List<String> arguments) {
        try {
            return Connecteur.appeler(url, arguments);
        } catch (ClientProtocolException e) {
            int erreur = ((HttpResponseException) e).getStatusCode();
            Constantes.logs.ajouterLog(String.valueOf(erreur));
            return String.valueOf(erreur);
        } catch (IOException e) {
            Constantes.logs.ajouterLog(e.getMessage());
            e.printStackTrace();
        } catch (URISyntaxException e) {
            Constantes.logs.ajouterLog(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
