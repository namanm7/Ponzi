
public class Location {
	private int population;
	private int pop_density;
	private int gdp_capita;
	private int feds;
	private int recruited;
	private String name;
	
	public Location(int population, int pop_density, int gdp_capita, int feds, 
			int recruited, String name){
		this.population = population;
		this.pop_density = pop_density;
		this.gdp_capita = gdp_capita;
		this.feds = feds;
		this.recruited = recruited;
		this.name = name;
	}
	/**
	 * 
	 * @return the population of the location
	 */
	public int getPopulation() {
		return population;
	};
	
	/**
	 * 
	 * @return the population density of the location
	 */
	public int getPopDensity() {
		return pop_density;
	};
	
	/**
	 * 
	 * @return the GDP per Capita of the location
	 */
	public int getGDPCapita() {
		return gdp_capita;
	};
	
	/**
	 * 
	 * @return the Fed score of the location
	 */
	public int getFeds() {
		return feds;
	};
	
	/**
	 * 
	 * @return the number recruited in the location
	 */
	public int getRecruited() {
		return recruited;
	};
	
	/**
	 * 
	 * @return the name of the location
	 */
	public String getName() {
		return name;
	}
	/**
	 * Adds a new number of recruits to the current number of recruits
	 * @param newRec the number of new recruits this cycle
	 */
	public void setRecruited(int newRec) {
		recruited = newRec;
	};
	
}

