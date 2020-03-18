import java.util.*;

public class Gameplay {
	int recruited;
	int econ_stability;
	int caught_probability;
	Player player;
	ArrayList<Location> Globe = new ArrayList<Location>();
	Location startLocation;
	
	/**
	 * Allows the game to play out
	 * @param players the players available in the game
	 */
	public Gameplay(Player[] players, ArrayList<Location> locations){
		startGame(players, locations);
	}
	
	/**
	 * Initializes everything needed to start the game
	 * @param players the players available in the game
	 */
	public void startGame(Player[] players, ArrayList<Location> locations) {
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
	
	public void chooseStartLocation(ArrayList<Location> locations) {
		int counter = 0;
		System.out.println("Select the number of the location you would like to begin your Ponzi Scheme in");
		for (Location l : locations) {
			counter++;
			System.out.println(counter + "). " + l.getName());
		}
		
		Scanner in = new Scanner(System.in);
		
		int choice;
		try {
			choice = in.nextInt();
		} catch (InputMismatchException s) {
			System.out.println("Invalid input. Please try again.");
			chooseStartLocation(locations);
			return;
		}
		if (choice <= 0 || choice > locations.size()) {
			System.out.println("Invalid input. Please try again.");
			chooseStartLocation(locations);
			return;
		}
		
		startLocation = locations.get(choice - 1);
		System.out.println("You selected " + startLocation.getName());
	}

}
