package htw.control;

import htw.model.Direction;
import java.awt.Component;

/**
 * Features the controller provides.
 */
public interface Controller {

	/**
	 * Start game, add listeners to view.
	 */
	void start();

	/**
	 * Exit program.
	 */
	void exitProgram();

	/**
	 * Stop current game and return to welcome page.
	 */
	void stopGame();

	/**
	 * Start a new game, ask user to input parameters.
	 */
	void restartNewGame();

	/**
	 * Restart the game with same setup as previous game.
	 */
	void restartSameGame();

	/**
	 * Create maze based on parameters input by user.
	 *
	 * @param numberOfRows           the number of rows of the maze
	 * @param numberOfColumns        the number of the columns of the maze
	 * @param startingPoint          the starting point of the maze
	 * @param percentageOfPits       percentage of caves containing pits
	 * @param percentageOfBats       percentage of caves containing bats
	 * @param numberOfArrow          the number of arrows that player has
	 * @param seed                   same seed will guarantee getting same sequence
	 *                               of random number in different instances of
	 *                               Random
	 * @param numberOfRemainingWalls the number of walls remaining
	 */
	void createMaze(String numberOfPlayers, String wrappingType, String numberOfRows, String numberOfColumns,
			String startingPoint, String percentageOfPits, String percentageOfBats, String numberOfArrow, String seed,
			String numberOfRemainingWalls);

	/**
	 * Let the player move to adjacent cell based on the specified direction.
	 *
	 * @param direction the direction the player will move to
	 */
	void move(Direction direction);

	/**
	 * Let the player move to adjacent cell based on the specified id.
	 *
	 * @param id the id of cell the player will move to
	 */
	void move(int id);

	/**
	 * Responds to the click on the cell.
	 *
	 * @param id the cell id
	 */
	void processClick(int id);

	/**
	 * Show about page.
	 */
	void showAbout();

	/**
	 * Responds to right mouse click to display popup menu.
	 *
	 * @param component the component
	 * @param id        the cell id
	 */
	void processPopup(Component component, int id);

	/**
	 * Handle the command to shoot arrow.
	 */
	void shoot();

	/**
	 * Cancel creating a new game.
	 */
	void cancelCreation();
}
