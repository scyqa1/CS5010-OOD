package htw.model;

import java.util.List;
import java.util.Map;

/**
 * Readonly model interface for view.
 */
public interface ReadonlyModel {
	/**
	 * Returns the number of rows of the maze.
	 *
	 * @return the number of rows of the maze
	 */
	int getNumberOfRows();

	/**
	 * Returns the number of columns of the maze.
	 *
	 * @return the number of columns of the maze
	 */
	int getNumberOfColumns();

	/**
	 * Returns the current location coordinates of the player.
	 *
	 * @return the current location coordinates of the player
	 */
	int[] getPlayerCurrentLocation();

	/**
	 * Returns the current location ID of the player.
	 *
	 * @return the current location ID of the player
	 */
	int getPlayerCurrentLocationId();

	int getPlayerCurrentLocationId(int playerId);

	/**
	 * Returns the previous location ID of the player.
	 *
	 * @return the previous location ID of the player
	 */
	int getPlayerPreviousLocationId();

	/**
	 * Returns the possible directions the player can move.
	 *
	 * @param currentId current ID
	 * @return the possible directions the player can move
	 */
	List<Direction> getPossibleMove(int currentId);

	/**
	 * Returns the adjacent location id where the player can move.
	 *
	 * @param currentId current ID
	 * @return the adjacent location id
	 */
	List<Integer> getAdjacentLocationId(int currentId);

	/**
	 * Returns the type of current cell.
	 *
	 * @return the type of current cell
	 */
	NodeType getCurrentLocationType();

	/**
	 * Return what we can smell at current location.
	 *
	 * @param listOfDirection the directions from which it smells
	 * @return the result of the smell or feel
	 */
	String smell(List<Direction> listOfDirection);

	/**
	 * Return what we can smell at current location.
	 *
	 * @param id the directions from which it smells
	 * @return the result of the smell or feel
	 */
	boolean[] smellAndFeel(int id);

	/**
	 * Return if the game is finished.
	 *
	 * @return if the game is finished
	 */
	boolean getIfGameEnd();

	/**
	 * Return if the location has pit, bat or wumpus.
	 *
	 * @return difficulty including if there is a pit, bat, wumpus
	 */
	Map<Obstacle, Boolean> getCurrentDifficulty();

	/**
	 * Return if the location has pit, bat or wumpus based on given location id.
	 *
	 * @return difficulty including if there is a pit, bat, wumpus
	 */
	Map<Obstacle, Boolean> getCurrentDifficulty(int id);

	/**
	 * Return the player id of next turn.
	 *
	 * @return the player id of next turn
	 */
	int whoIsNext();

	/**
	 * Return the player id of current turn.
	 *
	 * @return the player id of current turn
	 */
	int getTurn();

	/**
	 * Return the starting point.
	 *
	 * @return the starting point
	 */
	int getStartingPoint();

	/**
	 * Return the number of players.
	 *
	 * @return the number of players
	 */
	int getNumberOfPlayers();

	/**
	 * Return the number of arrows.
	 *
	 * @return the number of arrows
	 */
	int getNumberOfArrows();
}
