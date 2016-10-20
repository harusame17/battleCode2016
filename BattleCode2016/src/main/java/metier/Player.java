package metier;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String playerId;
	

	private String playerName;
	

	public List<Fighter> fighters;
	
	
	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}



	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}



	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}



	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}




	public Player() {
		fighters = new ArrayList<Fighter>();
	}


	/**
	 * @return the fighters
	 */
	public List<Fighter> getFighters() {
		return fighters;
	}



	/**
	 * @param fighters the fighters to set
	 */
	public void setFighters(List<Fighter> fighters) {
		this.fighters = fighters;
	}
	
	
	
	
}
