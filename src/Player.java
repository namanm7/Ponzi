
public class Player {
	
	int likeability;
	int risk;
	int strategy;
	int experience;
	String name;
	
	public Player(int likeability, int risk, int strategy, int experience, 
			String name) {
		this.likeability = likeability;
		this.risk = risk;
		this.strategy = strategy;
		this.experience = experience;
		this.name = name;
	}
	/**
	 * 
	 * @return the likeability score of the player
	 */
	public int getLikeability() {
		return likeability;
	}
	
	/**
	 * 
	 * @return the risk score of the player
	 */
	public int getRisk() {
		return risk;
	}
	
	/**
	 * 
	 * @return the strategy score of the player
	 */
	public int getStrategy() {
		return strategy;
	}
	
	/**
	 * 
	 * @return the experience score of the player
	 */
	public int getExperience() {
		return experience;
	}
	
	/**
	 * 
	 * @return the name of the player
	 */
	public String getName(){
		return name;
	}

}
