package htw.control;

import htw.model.Maze;
import htw.model.NonPerfectMaze;
import htw.model.Direction;
import htw.model.WrappingNonPerfectMaze;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * A controller for HTW game. This calculator receives all its inputs from a
 * Readable object and transmits all outputs to an Appendable object.
 */
public class ControllerImpl implements Controller {
	private final Readable in;
	private final Appendable out;
	private Maze model;

	int numberOfPlayers = 1;
	int wrappingType;
	int numberOfRows;
	int numberOfColumns;
	int startingPoint;
	int numberOfPits;
	int numberOfBats;
	int numberOfArrow;
	long seed;
	int numberOfRemainingWalls;

	/**
	 * Constructor for console controller.
	 *
	 * @param in  input
	 * @param out output
	 */
	public ControllerImpl(Readable in, Appendable out) {
		if (in == null || out == null) {
			throw new IllegalArgumentException("Readable and Appendable can't be null");
		}
		this.in = in;
		this.out = out;
	}

	/**
	 * Method that gives control to the controller.
	 *
	 * @throws IOException if something goes wrong appending to out
	 */
	public void start() {
		try {
			Scanner scan = new Scanner(this.in);
			this.out.append("\nPlease input wrapping type (wrapping 0, non-wrapping 1): ");
			this.wrappingType = Integer.parseInt(scan.next());
			this.out.append("\nPlease input number of rows: ");
			this.numberOfRows = Integer.parseInt(scan.next());
			this.out.append("\nPlease input number of columns: ");
			this.numberOfColumns = Integer.parseInt(scan.next());
			this.out.append("\nPlease input starting point: ");
			this.startingPoint = Integer.parseInt(scan.next());
			this.out.append("\nPlease input numberOfPits: ");
			this.numberOfPits = Integer.parseInt(scan.next());
			this.out.append("\nPlease input numberOfBats: ");
			this.numberOfBats = Integer.parseInt(scan.next());
			this.out.append("\nPlease input numberOfArrow: ");
			this.numberOfArrow = Integer.parseInt(scan.next());
			this.seed = 570834998;
			this.out.append("\nPlease input numberOfRemainingWalls: ");
			this.numberOfRemainingWalls = Integer.parseInt(scan.next());

			if (wrappingType == 1) {
				// create the model
				this.model = new NonPerfectMaze(numberOfRows, numberOfColumns, startingPoint, numberOfPits,
						numberOfBats, numberOfArrow, seed, numberOfPlayers, numberOfRemainingWalls);
			}
			if (wrappingType == 0) {
				// create the model
				this.model = new WrappingNonPerfectMaze(numberOfRows, numberOfColumns, startingPoint, numberOfPits,
						numberOfBats, numberOfArrow, seed, numberOfPlayers, numberOfRemainingWalls);
			}
			this.out.append("\nThe maze has been created.");
			Objects.requireNonNull(model);
			while (!model.getIfGameEnd()) {
				List<Direction> directionsList = model.getPossibleMove(model.getPlayerCurrentLocationId());
				this.out.append("\n");
				this.out.append(model.smell(directionsList));
				this.out.append("You are in cave (" + model.getPlayerCurrentLocation()[0] + ", "
						+ model.getPlayerCurrentLocation()[1] + ")\n");
				List<String> directionsStringList = new ArrayList<>();
				for (Direction d : directionsList) {
					directionsStringList.add(d.toString().substring(0, 1));
				}
				this.out.append("Tunnels lead to "
						+ directionsStringList.toString().substring(1, directionsStringList.toString().length() - 1)
						+ "\n");
				this.out.append("\nShoot or Move (S-M)? ");
				String command = scan.next();
				switch (command) {
				case "M":
					while (true) {
						this.out.append("Where to? ");
						String whereTo = scan.next();
						if (!directionsStringList.contains(whereTo)) {
							this.out.append("The direction is not valid, input again\n");
							continue;
						} else {
							String moveResult = model.movePlayerTo(string2Direction(whereTo));
							this.out.append(moveResult);
							break;
						}
					}
					break;
				case "S":
					while (true) {
						this.out.append("No. of caves (1-5)? ");
						String distanceString = scan.next();
						if (!isInteger(distanceString)) {
							this.out.append("The No. of caves is not a integer, input again\n");
							continue;
						} else if (Integer.parseInt(distanceString) < 1 || Integer.parseInt(distanceString) > 5) {
							this.out.append("The No. of caves is not valid, input again\n");
							continue;
						} else {
							int distance = Integer.parseInt(distanceString);
							while (true) {
								this.out.append("Where to? ");
								String arrowTo = scan.next();
								if (!directionsStringList.contains(arrowTo)) {
									this.out.append("The direction is not valid, input again\n");
								} else {
									int[] shootResult = model.shoot(string2Direction(arrowTo), distance);
									if (shootResult[0] == 0) {
										this.out.append("run out of arrows");
									} else if (shootResult[0] == 1) {
										this.out.append("arrow hit the wall at location " + shootResult[1]);
									} else if (shootResult[0] == 2) {
										this.out.append("you killed the wumpus at location " + shootResult[1]);
									} else if (shootResult[0] == 3) {
										this.out.append("you didn't got the wumpus at location " + shootResult[1]);
									}
									break;
								}
							}
						}
						break;
					}
					break;
				case "q":
					scan.close();
					return;
				default:
					this.out.append("The command is not valid, input again, q for exit\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopGame() {
		return;
	}

	@Override
	public void exitProgram() {
		return;
	}

	@Override
	public void restartNewGame() {
		return;
	}

	@Override
	public void restartSameGame() {
		return;
	}

	@Override
	public void createMaze(String numberOfPlayers, String wrappingType, String numberOfRows, String numberOfColumns,
			String startingPoint, String percentageOfPits, String percentageOfBats, String numberOfArrow, String seed,
			String numberOfRemainingWalls) {
		return;
	}

	@Override
	public void move(Direction direction) {
		return;
	}

	@Override
	public void move(int id) {
		return;
	}

	@Override
	public void processClick(int id) {
		return;
	}

	@Override
	public void showAbout() {
		return;
	}

	@Override
	public void processPopup(Component component, int id) {
		return;
	}

	@Override
	public void shoot() {
		return;
	}

	@Override
	public void cancelCreation() {
		return;
	}

	private Direction string2Direction(String direction) throws IllegalArgumentException {
		switch (direction) {
		case "N":
			return Direction.NORTH;
		case "S":
			return Direction.SOUTH;
		case "W":
			return Direction.WEST;
		case "E":
			return Direction.EAST;
		default:
			throw new IllegalArgumentException("Direction is unsupported");
		}
	}

	private boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}
}
