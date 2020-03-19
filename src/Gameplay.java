import java.util.*;

public class Gameplay {
	int recruited;
	int econ_stability;
	int caught_probability;
	int total_pop;
	Player player;
	HashMap<String, Location> globe = new HashMap<String,Location>();
	String startLocation;
	String[] options = new String[] {"Recruit Within", "Spread Outside", 
	"Deceive Feds"};
	
	/**
	 * Allows the game to play out
	 * @param players the players available in the game
	 */
	public Gameplay(Player[] players, HashMap<String, Location> locations){
		recruited = 0;
		econ_stability = 100;
		caught_probability = 0;
		globe = locations;
		startGame(players, locations);
		total_pop = 0;
		for (Location l : locations.values()) {
			total_pop += l.getPopulation();
		}
		buildScheme();
	}
	
	
	private void buildScheme() {
		System.out.printf("%.2f", (double) (recruited / total_pop));
		System.out.println("% : Total Recruited Percentage");
		
		
		
		System.out.println(econ_stability + ": Economic Stability out of 100");
		System.out.println(caught_probability + "% : Probability of being caught \n");
		listOptions();
	}
	
	private void listOptions() {
		System.out.println("Choose the number of the option you would like");
		int counter= 0;
		for (String s : options) {
			counter++;
			System.out.println(counter + "). " + s);
		}
		Scanner in = new Scanner(System.in);
		
		int choice;
		try {
			choice = in.nextInt();
		} catch (InputMismatchException s) {
			System.out.println("Invalid input. Please try again.");
			listOptions();
			return;
		}
		if (choice <= 0 || choice > options.length) {
			System.out.println("Invalid input. Please try again.");
			listOptions();
			return;
		}
		
		makeDecision(choice);
	}
	
	private void makeDecision(int choice) {
		switch(choice) {
		case 1: 
			recruitWithin();
			break;
		case 2:
			spreadOutside();
			break;
		case 3:
			deceiveFeds();
			break;
		default:
			break;
		}
	}
	
	private void recruitWithin() {
		Location current = globe.get(startLocation);
		int curr_pop = current.getPopulation();
		int curr_recruited = current.getRecruited();
		int curr_density = current.getPopDensity();
		int curr_gdp = current.getGDPCapita();
		int curr_feds = current.getFeds();
		
		int newRecruits = (int) (Math.random() * curr_pop / 100);
		newRecruits += (int) (Math.random() * 5) * curr_density;
		//Gives a 5% chance to increase the amount of recruited by the total GDP
		newRecruits += (int) (Math.random() * 20) > 18 ? curr_gdp : 0;
		
		int newTotal = newRecruits + curr_recruited;
		if (newTotal >= curr_pop)
			current.setRecruited(curr_pop);
		else 
			current.setRecruited(newTotal);
		
		int add_caught = (int) (Math.random() * curr_feds) > 4 ? (curr_feds / 2) : 0;
		caught_probability += add_caught;
		
		checkCaught();
	}
	
	private void spreadOutside() {
		
	}
	
	private void deceiveFeds() {
		
	}
	
	private void checkCaught() {
		
	}


	/**
	 * Initializes everything needed to start the game
	 * @param players the players available in the game
	 */
	private void startGame(Player[] players, HashMap<String, Location> locations) {
		choosePlayer(players);
		chooseStartLocation(locations);
	}
	
	/**
	 * Allows the user to choose a player. Sets their choice to player
	 * @param players the players available in the game
	 */
	private void choosePlayer(Player[] players) {
		int counter = 0;
		System.out.println("Select the number of the Player you would like");
		for (Player p : players) {
			counter++;
			System.out.println(counter + "). " + p.getName());
		}
		Scanner in = new Scanner(System.in);
		
		int choice;
		try {
			choice = in.nextInt();
		} catch (InputMismatchException s) {
			System.out.println("Invalid input. Please try again.");
			choosePlayer(players);
			return;
		}
		if (choice <= 0 || choice > players.length) {
			System.out.println("Invalid input. Please try again.");
			choosePlayer(players);
			return;
		}
		
		player = players[choice - 1];
		System.out.println("You selected " + player.getName());
	}
	
	private void chooseStartLocation(HashMap<String, Location> locations) {
		int counter = 0;
		System.out.println("Select the name of the location you would like to begin your Ponzi Scheme in");
		for (Location l : locations.values()) {
			counter++;
			System.out.println("- " + l.getName());
		}
		
		Scanner in = new Scanner(System.in);
		
		String choice;
		try {
			choice = in.nextLine();
		} catch (InputMismatchException s) {
			System.out.println("Invalid input. Please try again.");
			chooseStartLocation(locations);
			return;
		}
		if (!locations.containsKey(choice)) {
			System.out.println("Invalid input. Please try again.");
			chooseStartLocation(locations);
			return;
		}
		
		startLocation = choice;
		System.out.println("You selected " + choice);
	}

}
