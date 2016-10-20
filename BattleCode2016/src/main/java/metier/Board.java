
package metier;

import java.util.List;

public class Board {

    private Player player1;
    private Player player2;

    private List<Player> playerBoards;

    private String playerMoves;

    private int nbrTurnsLeft;
    
    
    public Board() {
    }

    public List<Player> getPlayerBoards() {
        return playerBoards;
    }

    public void setPlayerBoards(List<Player> playerBoards) {
        this.playerBoards = playerBoards;
    }

    public Player getPlayer1() {
        return player1;
    }


    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }


    public Player getPlayer2() {
        return player2;
    }


    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }


   public int getNbrTurnsLeft() {
       return nbrTurnsLeft;
    }


   public void setNbrTurnsLeft(int nbrTurnsLeft) {
       this.nbrTurnsLeft = nbrTurnsLeft;
    }

    public String getPlayerMoves() {
        return playerMoves;
    }

    public void setPlayerMoves(String playerMoves) {
        this.playerMoves = playerMoves;
    }

    @Override
    public String toString() {
       return "Board \n\tplayer1=" + player1 + "\n\tplayer2=" + player2 + "\n\tnbrActionleft="
               + nbrTurnsLeft + "]";
    }

}