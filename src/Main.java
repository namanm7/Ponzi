import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player[] players = new Player[] {new Player(10,5,5,5,"Bridget"),
				new Player(5,5,10,5,"Christine"),
				new Player(5,5,5,5,"Kathryn"),
				new Player(5,5,5,10,"Ryan"),
				new Player(5,10,5,5,"Sam")};
		ArrayList<Location> globe = new ArrayList<Location>();
		globe.add(new Location(1432132, 34653, 29200, 2, 0, "The Bronx"));
		globe.add(new Location(2582830, 37137, 34600, 8, 0, "Brooklyn"));
		globe.add(new Location(1628701, 72033, 360900, 10, 0, "Manhattan"));
		globe.add(new Location(2278906, 21460, 39600, 6, 0, "Queens"));
		globe.add(new Location(476179, 8112, 30300, 4, 0, "Staten Island"));
		Gameplay g = new Gameplay(players, globe);

	}

}
