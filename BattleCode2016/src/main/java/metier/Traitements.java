package metier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;

import com.google.gson.Gson;

import connecteur.Connecteur;
import transverse.Constantes;

public class Traitements {

    private static String idEquipe = null;

    private static String idPartie = null;

    private static int tourExplodedBomb1 = 99;

    private static int tourExplodedBomb2 = 99;

    private static String lastShoot = null;

    private static int bombeAmieLancee1 = 99;

    private static int bombeAmieLancee2 = 99;

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
    public static void traiterGoBot() {
        Constantes.logs.ajouterLog("\n");
        traiterGetIdEquipe();

        idPartie = Constantes.NA;
        while (Constantes.NA.equals(idPartie)) {
            idPartie = appeler(Constantes.NEW_BOT, Arrays.asList("5", idEquipe));
        }

        String status = traitementJeu();

        if (Constantes.GAGNE.equals(status)) {
            Constantes.logs.ajouterLog("==== VICTOIRE DE LA TEAM !! ===");
        } else if (Constantes.PERDU.equals(status)) {
            Constantes.logs.ajouterLog("=== MALHEUREUSE DEFAITE... ===");
        }

    }

    // game/getlastmove/{idPartie}
    public static void traiterGoVersus() {
        Constantes.logs.ajouterLog("\n");
        traiterGetIdEquipe();

        idPartie = Constantes.NA;
        while (Constantes.NA.equals(idPartie)) {
            idPartie = appeler(Constantes.NEXT_JOUEUR, Arrays.asList(idEquipe));
        }

        String status = traitementJeu();

        if (Constantes.GAGNE.equals(status)) {
            Constantes.logs.ajouterLog("==== VICTOIRE DE LA TEAM !! ===");
        } else if (Constantes.PERDU.equals(status)) {
            Constantes.logs.ajouterLog("=== MALHEUREUSE DEFAITE... ===");
        }

    }

    private static String traitementJeu() {
        String status = Constantes.NON;
        String lastMove = null;
        while (!Constantes.GAMEOVER.equals(status) && !Constantes.GAGNE.equals(status)
                && !Constantes.PERDU.equals(status)) {
            while (Constantes.NON.equals(status) || Constantes.ANNULE.equals(status)) {
                status = appeler(Constantes.STATUS, Arrays.asList(idPartie, idEquipe));
            }

            if (Constantes.OUI.equals(status)) {
                Board plateau = extraitJson(appeler(Constantes.BOARD, Arrays.asList(idPartie)));

                lastMove = ia(lastMove, plateau);

                String retour = appeler(Constantes.PLAY, Arrays.asList(idPartie, idEquipe, lastMove));

                // Mauvais Coup
                if (Constantes.KO.equals(retour)) {
                    status = Constantes.PERDU;
                } else if (Constantes.GAMEOVER.equals(retour)) {
                    status = Constantes.GAGNE;
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

    private static String ia(String lastMove, Board plateau) {
        return iaSER(lastMove, plateau);
    }

    private static String iaJLN(String lastMove, Board plateau) {
        String dernierMouvement = appeler(Constantes.LAST_MOVE, Arrays.asList(idPartie, idEquipe));

        Constantes.logs.ajouterLog(plateau.toString());
        // Traitement metier

        Player nous = null;
        Player eux = null;

        if (Constantes.NOM_EQUIPE.equals(plateau.getPlayer1().getName())) {
            nous = plateau.getPlayer1();
            eux = plateau.getPlayer2();
        } else {
            nous = plateau.getPlayer2();
            eux = plateau.getPlayer1();
        }

        int notreNbBalles = nous.getBullet();
        int notreNbBouclie = nous.getShield();
        int notreNbVie = nous.getHealth();
        int notreNbBombe = nous.getBomb();

        int nbBallesAdverse = eux.getBullet();
        int nbBouclieAdverse = eux.getShield();
        int nbVieAdverse = eux.getHealth();
        int nbBombeAdverse = eux.getBomb();

        String mouvement = Constantes.SHOOT;

        if (notreNbBalles <= 1 || Math.random() > 0.5 && notreNbBalles < 4) {
            mouvement = Constantes.RELOAD;
        }

        if (Math.random() > 0.7 && notreNbBouclie > 5 && nbBallesAdverse >= 2) {
            mouvement = Constantes.COVER;
        }

        if ((nbBouclieAdverse < 5 || Math.random() > 0.5) && notreNbBombe > 0 && nbBallesAdverse == 0
                && nbVieAdverse >= 2) {
            mouvement = Constantes.BOMB;
        }

        if (Constantes.AIM.equals(dernierMouvement) && nbBallesAdverse > 0 && notreNbBouclie > 0) {
            if (notreNbBouclie > 3) {
                mouvement = Constantes.COVER;
            }
        }

        if (Constantes.BOMB.equals(dernierMouvement) && notreNbBouclie > 0) {
            mouvement = Constantes.COVER;
        }

        if (nbVieAdverse == 1 && notreNbBalles > 0) {
            mouvement = Constantes.SHOOT;
        }

        if (notreNbVie > nbVieAdverse && notreNbBouclie > plateau.getNbrActionLeft()) {
            mouvement = Constantes.COVER;
        }

        // System.out.println("LAST MOVE =" + lastMove);
        // System.out.println("LAST MOVE ADVERSE =" + dernierMouvement);
        return mouvement;
    }

    private static String iaSER(String lastMove, Board plateau) {
        String dernierMouvement = appeler(Constantes.LAST_MOVE, Arrays.asList(idPartie, idEquipe));
        Constantes.logs.ajouterLog(plateau.toString());

        // Traitement metier
        Player nous = null;
        Player eux = null;
        if (Constantes.NOM_EQUIPE.equals(plateau.getPlayer1().getName())) {
            nous = plateau.getPlayer1();
            eux = plateau.getPlayer2();

        } else {
            nous = plateau.getPlayer2();
            eux = plateau.getPlayer1();

        }
        int notreNbBalles = nous.getBullet();
        int notreNbBouclie = nous.getShield();
        int notreNbVie = nous.getHealth();
        int notreNbBombe = nous.getBomb();
        int nbBallesAdverse = eux.getBullet();

        int nbBouclieAdverse = eux.getShield();
        int nbVieAdverse = eux.getHealth();
        int nbBombeAdverse = eux.getBomb();
        String nextMove = Constantes.SHOOT;
        if (dernierMouvement.equals(Constantes.BOMB) && !lastShoot.equals(Constantes.SHOOT)) {
            if (tourExplodedBomb1 == 99) {
                tourExplodedBomb1 = plateau.getNbrActionLeft() - 2;
            } else if (tourExplodedBomb2 == 99) {
                tourExplodedBomb2 = plateau.getNbrActionLeft() - 2;
            } else {
                // c'est un boulet
            }
        }

        if (plateau.getNbrActionLeft() != 30) {
            if ((tourExplodedBomb1 == 99
                    || (tourExplodedBomb1 != 99 && tourExplodedBomb1 >= plateau.getNbrActionLeft()))
                    && (tourExplodedBomb2 == 99
                            || (tourExplodedBomb2 != 99 && tourExplodedBomb2 >= plateau.getNbrActionLeft()))
            ) {
                if (dernierMouvement.equals(Constantes.AIM) && notreNbVie >= 6) {
                    if (notreNbBouclie > 0 && !lastShoot.equals(Constantes.SHOOT)) {
                        nextMove = Constantes.COVER;
                    }

                } else if (!dernierMouvement.equals(Constantes.RELOAD) && nbBallesAdverse == 0
                        && plateau.getNbrActionLeft() > 2 && notreNbBombe > 0) {
                    nextMove = Constantes.BOMB;
                } // DerniËres verifs

                if (nextMove.equals(Constantes.SHOOT)) {

                    /*
                     * if (notreNbBalles < 2 && !dernierMouvement.equals(Constantes.AIM)) {
                     * 
                     * nextMove = Constantes.RELOAD;
                     * 
                     * } else
                     */ if (notreNbBalles < 1) {
                        nextMove = Constantes.RELOAD;
                    }

                    if (bombeAmieLancee1 != 99) {
                        if (((bombeAmieLancee1 - 2) <= plateau.getNbrActionLeft())
                                || ((bombeAmieLancee2 - 2) <= plateau.getNbrActionLeft())) {
                            if (notreNbBalles < 6) {
                                nextMove = Constantes.RELOAD;
                            } else {
                                nextMove = Constantes.AIM;
                            }
                        }
                    }

                } else if (nextMove.equals(Constantes.RELOAD)) {
                    if (notreNbBalles >= 6)
                        nextMove = Constantes.SHOOT;
                } else if (nextMove.equals(Constantes.BOMB)) {
                    if (notreNbBombe == 0)
                        nextMove = (notreNbBalles == 0) ? Constantes.RELOAD : Constantes.SHOOT;
                } else if (nextMove.equals(Constantes.COVER)) {
                    if (notreNbBouclie == 0 || lastShoot.equals(Constantes.COVER)) {
                        nextMove = (notreNbBalles == 0) ? Constantes.RELOAD : Constantes.SHOOT;
                    }

                }
                if (notreNbVie > nbVieAdverse && notreNbBouclie >= plateau.getNbrActionLeft()) {
                    nextMove = Constantes.COVER;
                }
            } else {
                if (notreNbBouclie != 0) {
                    nextMove = Constantes.COVER;
                } else {
                    nextMove = (notreNbBalles == 0) ? Constantes.RELOAD : Constantes.SHOOT;
                }
            }
        }

        lastShoot = nextMove;
        if (nextMove.equals(Constantes.BOMB)) {
            if (bombeAmieLancee1 == 99) {
                bombeAmieLancee1 = plateau.getNbrActionLeft();
            } else {
                bombeAmieLancee2 = plateau.getNbrActionLeft();
            }
        }
        return nextMove;
    }

    private static String iaJLL(String lastMove, Board plateau) {
        return Constantes.SHOOT;
    }

    private static String iaSAY(String lastMove, Board plateau) {
        return Constantes.SHOOT;
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
