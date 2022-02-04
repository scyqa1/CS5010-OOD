package htw.model;

/**
 * This class represents a perfect maze. Perfect maze has one and only one path
 * from any point in the maze to any other point in the maze. This means that
 * the maze has no inaccessible sections, no circular paths, no open areas.
 */
public class PerfectMaze extends AbstractMaze {

	/**
	 * Constructor for building the first part of a perfect maze.
	 *
	 * @param numberOfRows    the number of rows of the maze
	 * @param numberOfColumns the number of the columns of the maze
	 * @param startingPoint   the starting point of the maze
	 * @param seed            same seed will guarantee getting same sequence of
	 *                        random number in different instances of Random
	 * @param part            the first part of constructing a perfect maze
	 * @throws IllegalArgumentException if numberOfRows or numberOfColumns is less
	 *                                  than or equal to 0, if goal or startingPoint
	 *                                  is out of range
	 */
	protected PerfectMaze(int numberOfRows, int numberOfColumns, int startingPoint, int percentageOfPits,
			int percentageOfBats, int numberOfArrow, long seed, int numberOfPlayers, int part)
			throws IllegalArgumentException {
		super(numberOfRows, numberOfColumns, startingPoint, percentageOfPits, percentageOfBats, numberOfArrow, seed,
				numberOfPlayers);
	}

	/**
	 * Constructor for building a perfect maze.
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
	public PerfectMaze(int numberOfRows, int numberOfColumns, int startingPoint, int percentageOfPits,
			int percentageOfBats, int numberOfArrow, long seed, int numberOfPlayers) throws IllegalArgumentException {
		this(numberOfRows, numberOfColumns, startingPoint, percentageOfPits, percentageOfBats, numberOfArrow, seed,
				numberOfPlayers, 1);
		// remove walls at the same time create adjacency list
		buildMaze();
	}
}
