package htw.model;

/**
 * This interface contains all operations that all types of mazes should
 * support.
 */
public interface Maze extends ReadonlyModel {
	/**
	 * Let the player move to the specified direction.
	 *
	 * @param direction the direction the player will move to
	 * @return the result of the move
	 */
	String movePlayerTo(Direction direction);

	/**
	 * Let the player move to adjacent cell based on the specified direction.
	 *
	 * @param direction the direction the player will move to
	 */
	void move(Direction direction);

	/**
	 * Let the player move to a cell based on the specified location id.
	 *
	 * @param newId the new location id that the player will move to
	 */
	void move(int newId);

	/**
	 * A player can attempt to slay the Wumpus by specifying a direction and
	 * distance in which to shoot their crooked arrow. Distance is defined as the
	 * number of caves (but not tunnels) that an arrow travels. Arrows travel freely
	 * down tunnels (even crooked ones) but only travel a straight line through a
	 * cave. Return if it can kill the Wumpus.
	 *
	 * @param direction the direction the arrow shoot
	 * @param distance  the number of caves that arrow travels
	 * @return true if it can kill the Wumpus
	 */
	int[] shoot(Direction direction, int distance);

	/**
	 * Return the id of location to which bats take the player.
	 *
	 * @return the id of location to which bats take the player, -1 means player
	 *         ducked bats
	 */
	int encounterBats();

	/**
	 * Switch turn of player.
	 *
	 * @param playerId player id
	 */
	void switchTurn(int playerId);

	/**
	 * Update and return the game result.
	 *
	 * @return game result
	 */
	String updateAndReturnGameResult();

	/**
	 * Set the player to be dead.
	 */
	void setDead();
}
