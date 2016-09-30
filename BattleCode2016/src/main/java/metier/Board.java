package metier;

public class Board {

	private Player player1;
	private Player player2;

    private int nbrActionLeft;
	
	
	public Board() {
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


    public int getNbrActionLeft() {
        return nbrActionLeft;
	}


    public void setNbrActionLeft(int nbrActionLeft) {
        this.nbrActionLeft = nbrActionLeft;
	}


	@Override
	public String toString() {
        return "Board \n\tplayer1=" + player1 + "\n\tplayer2=" + player2 + "\n\tnbrActionleft="
                + nbrActionLeft + "]";
	}

}
