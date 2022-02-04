package htw.model;

/**
 * This class represents a wrapping non perfect maze.
 */
public class WrappingNonPerfectMaze extends WrappingPerfectMaze {

	/**
	 * Constructor for building a wrapping non perfect maze.
	 *
	 * @param numberOfRows           the number of rows of the maze
	 * @param numberOfColumns        the number of the columns of the maze
	 * @param startingPoint          the starting point of the maze
	 * @param seed                   same seed will guarantee getting same sequence
	 *                               of random number in different instances of
	 *                               Random
	 * @param numberOfRemainingWalls the number of walls remaining
	 * @throws IllegalArgumentException if numberOfRows or numberOfColumns is less
	 *                                  than or equal to 0, if goal or startingPoint
	 *                                  is out of range, if numberOfRemainingWalls
	 *                                  is out of range
	 */
	public WrappingNonPerfectMaze(int numberOfRows, int numberOfColumns, int startingPoint, int percentageOfPits,
			int percentageOfBats, int numberOfArrow, long seed, int numberOfPlayers, int numberOfRemainingWalls)
			throws IllegalArgumentException {
		super(numberOfRows, numberOfColumns, startingPoint, percentageOfPits, percentageOfBats, numberOfArrow, seed,
				numberOfPlayers);
		if (numberOfRemainingWalls < 0 || numberOfRemainingWalls >= walls.size()) {
			throw new IllegalArgumentException("Invalid number of remaining walls");
		}
		this.numberOfRemainingWalls = numberOfRemainingWalls;
		buildNonPerfectMaze();
	}
}
