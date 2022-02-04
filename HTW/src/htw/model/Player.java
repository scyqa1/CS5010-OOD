package htw.model;

/**
 * Package visible class Player represents a player in the maze.
 */
class Player {
	Location currentLocation;
	Location previousLocation = null;
	int numberOfArrow;
	boolean isAlive = true;
	boolean isWin = false;

	Player(Location startingPoint, int numberOfArrow) {
		this.numberOfArrow = numberOfArrow;
		this.currentLocation = startingPoint;
	}
}
