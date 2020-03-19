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
	int deception_counter = 0;
	
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
		for (Location l : globe.values()) {
			total_pop += l.getPopulation();
		}
		buildScheme();
	}
	
	
	private void buildScheme() {
		recruited = 0;
		for (Location l : globe.values()) {
			recruited += l.getRecruited();
			System.out.printf("%.2f", (double) l.getRecruited() 
					/ l.getPopulation() * 100);
			System.out.println("% : " + l.getName() + " Recruited Percentage");
		}
		System.out.printf("%.2f", (double) recruited / total_pop * 100);
		System.out.println("% : Total Recruited Percentage");
		
		System.out.println(econ_stability + ": Economic Stability out of 100");
		System.out.println(caught_probability + "% : Probability of being caught \n");
		listOptions();
		if (checkCaught()) {
			return;
		}
		buildScheme();	
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
		calculateNewRecruits(current);
	}
	
	private void calculateNewRecruits(Location current) {
		int curr_pop = current.getPopulation();
		int curr_recruited = current.getRecruited();
		int curr_density = current.getPopDensity();
		int curr_gdp = current.getGDPCapita();
		int curr_feds = current.getFeds();
		int risk = player.getRisk();
		int strategy = player.getStrategy();
		int likeability = player.getLikeability();
		
		int newRecruits = (int) (Math.random() * curr_pop / 50);
		newRecruits += (int) (Math.random() * 5) * curr_density;
		//Gives a 1/3 chance to increase the amount of recruited by the total GDP
		newRecruits += (int) (Math.random() * 3) == 0  ? curr_gdp : 0;
		
		newRecruits += (int) (Math.random() * 12) < strategy ? strategy * 10000 : 0;
		newRecruits += (int) (Math.random() * 12) < likeability ? likeability * 10000 : 0;
		
		
		int newTotal = newRecruits + curr_recruited;
		if (newTotal >= curr_pop)
			current.setRecruited(curr_pop);
		else 
			current.setRecruited(newTotal);
		
		int add_caught = 
				(int) (Math.random() * curr_feds) > 4 ? curr_feds / 2 : 0;
				
		add_caught += (int) (Math.random() * 15) < risk ? risk : 0;
		caught_probability += add_caught;
		globe.replace(current.getName(), current);
	}
	
	private void spreadOutside() {
		System.out.println("Select the name of the location where you would like to spread your Ponzi Scheme");
		for (Location l : globe.values()) {
			System.out.println("- " + l.getName());
		}
		
		Scanner in = new Scanner(System.in);
		
		String choice;
		try {
			choice = in.nextLine();
		} catch (InputMismatchException s) {
			System.out.println("Invalid input. Please try again.");
			spreadOutside();
			return;
		}
		if (!globe.containsKey(choice)) {
			System.out.println("Invalid input. Please try again.");
			spreadOutside();
			return;
		}
		calculateNewRecruits(globe.get(choice));
	}
	
	private void deceiveFeds() {
		if (deception_counter >= 10)
			return;
		int deceive = (int) (Math.random() * 10);
		if (deceive < player.experience)
		{
			caught_probability -= deceive;
			if (caught_probability < 0)
				caught_probability = 0;
		}
		deception_counter++;
	}
	
	private boolean checkCaught() {
		if (caught_probability >= 100) {
			System.out.println("You have been caught by the SEC! Game Over");
			return true;
		}
		return false;
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
		System.out.println("Select the name of the location you would like to begin your Ponzi Scheme in");
		for (Location l : locations.values()) {
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
