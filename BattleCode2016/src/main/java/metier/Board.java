package metier;

public class Board {

	private Player player1;
	private Player player2;
	private int nbrActionleft;
	
	
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


	public int getNbrActionleft() {
		return nbrActionleft;
	}


	public void setNbrActionleft(int nbrActionleft) {
		this.nbrActionleft = nbrActionleft;
	}


	@Override
	public String toString() {
		return "Board [player1=" + player1 + ", player2=" + player2
				+ ", nbrActionleft=" + nbrActionleft + "]";
	}

}
