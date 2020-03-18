import java.util.*;

public class Gameplay {
	int recruited;
	int econ_stability;
	int caught_probability;
	Player player;
	HashMap<String, Location> globe = new HashMap<String,Location>();
	Location startLocation;
	
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
		buildScheme();
	}
	
	
	public void buildScheme() {
		listOptions();
	}
	
	public void listOptions() {
		String[] options = new String[] {"Recruit Within", "Spread Outside", 
				"Deceive Feds"};
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
	}


	/**
	 * Initializes everything needed to start the game
	 * @param players the players available in the game
	 */
	public void startGame(Player[] players, HashMap<String, Location> locations) {
		choosePlayer(players);
		chooseStartLocation(locations);
	}
	
	/**
	 * Allows the user to choose a player. Sets their choice to player
	 * @param players the players available in the game
	 */
	public void choosePlayer(Player[] players) {
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
	
	public void chooseStartLocation(HashMap<String, Location> locations) {
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
		
		startLocation = locations.get(choice);
		System.out.println("You selected " + startLocation.getName());
	}

}
