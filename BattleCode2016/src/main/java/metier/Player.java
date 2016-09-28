package metier;

public class Player {

	private String name;
	private int health, bullet, shield;
	private boolean focused;

	public Player() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getBullet() {
		return bullet;
	}

	public void setBullet(int bullet) {
		this.bullet = bullet;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", health=" + health + ", bullet="
				+ bullet + ", shield=" + shield + ", focused=" + focused + "]";
	}
	
}
