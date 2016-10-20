package metier;

public class State {

	public String type;
	public int remainingDuration;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the remainingDuration
	 */
	public int getRemainingDuration() {
		return remainingDuration;
	}
	/**
	 * @param remainingDuration the remainingDuration to set
	 */
	public void setRemainingDuration(int remainingDuration) {
		this.remainingDuration = remainingDuration;
	}
}
