package htw.model;

/**
 * This class represents a wrapping perfect maze. Its locations at the top,
 * bottom, left and right can "wrap" to the other side.
 */
public class WrappingPerfectMaze extends PerfectMaze {

	/**
	 * Constructor for building a wrapping perfect maze.
	 *
	 * @param numberOfRows    the number of rows of the maze
	 * @param numberOfColumns the number of the columns of the maze
	 * @param startingPoint   the starting point of the maze
	 * @param seed            same seed will guarantee getting same sequence of
	 *                        random number in different instances of Random
	 * @throws IllegalArgumentException if numberOfRows or numberOfColumns is less
	 *                                  than or equal to 0, if goal or startingPoint
	 *                                  is out of range
	 */
	public WrappingPerfectMaze(int numberOfRows, int numberOfColumns, int startingPoint, int percentageOfPits,
			int percentageOfBats, int numberOfArrow, long seed, int numberOfPlayers) throws IllegalArgumentException {
		super(numberOfRows, numberOfColumns, startingPoint, percentageOfPits, percentageOfBats, numberOfArrow, seed,
				numberOfPlayers, 1);
		// add walls on the side of the maze
		addSideWalls();
		// randomly remove walls, add path to the adjacency list
		buildMaze();
	}
}
