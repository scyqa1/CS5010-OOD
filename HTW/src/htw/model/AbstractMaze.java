package htw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.round;

/**
 * This abstract class contains all the fields and methods that all concrete
 * maze classes are sharing.
 */
public abstract class AbstractMaze implements Maze {
	protected int numberOfRows;
	protected int numberOfColumns;
	protected int n;
	protected int startingPoint;
	protected Player[] players;
	protected List<Edge> walls;
	protected Location[] locations;
	protected Random random;
	protected int numberOfRemainingWalls;
	protected static final int NUMBER_OF_WUMPUS = 1;
	protected int numberOfPits;
	protected int numberOfBats;
	protected boolean ifGameEnd = false;
	protected int numberOfPlayers = 1;
	protected int turn;

	/**
	 * Protected constructor for use by subclasses.
	 *
	 * @param numberOfRows     the number of rows of the maze
	 * @param numberOfColumns  the number of the columns of the maze
	 * @param startingPoint    the starting point of the maze
	 * @param percentageOfPits percentage of caves containing pits
	 * @param percentageOfBats percentage of caves containing bats
	 * @param numberOfArrow    the number of arrows that player has
	 * @param seed             same seed will guarantee getting same sequence of
	 *                         random number in different instances of Random
	 * @throws IllegalArgumentException if numberOfRows or numberOfColumns is less
	 *                                  than or equal to 0, if goal or startingPoint
	 *                                  is out of range
	 */
	protected AbstractMaze(int numberOfRows, int numberOfColumns, int startingPoint, int numberOfPits, int numberOfBats,
			int numberOfArrow, long seed, int numberOfPlayers) throws IllegalArgumentException {
		if (numberOfRows <= 0 || numberOfColumns <= 0) {
			throw new IllegalArgumentException("Number of rows and number of columns should be greater than 0");
		}
		if (startingPoint >= numberOfRows * numberOfColumns || startingPoint < 0) {
			throw new IllegalArgumentException("StartingPoint is out of range");
		}
		if (numberOfArrow <= 0) {
			throw new IllegalArgumentException("Number of arrows should be greater than 0");
		}
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.n = numberOfRows * numberOfColumns;
		this.startingPoint = startingPoint;
		this.random = new Random(seed);
		this.numberOfPlayers = numberOfPlayers;
		this.turn = 0;
		this.numberOfPits = numberOfPits;
		this.numberOfBats = numberOfBats;
		// create all edges/walls
		createWalls(numberOfRows, numberOfColumns);
		// create locations/rooms/nodes/hallways etc.
		createLocations(numberOfRows, numberOfColumns);
		// create all players
		this.players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player(locations[startingPoint], numberOfArrow);
		}
	}

	@Override
	public int whoIsNext() {
		int tmp = (turn + 1) % numberOfPlayers;
		if (!players[tmp].isAlive) {
			tmp = (tmp + 1) % numberOfPlayers;
		}
		return tmp;
	}

	@Override
	public int getTurn() {
		return turn;
	}

	@Override
	public int getStartingPoint() {
		return this.startingPoint;
	}

	@Override
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	@Override
	public int getNumberOfArrows() {
		return players[turn].numberOfArrow;
	}

	@Override
	public void switchTurn(int playerId) {
		turn = playerId;
	}

	protected void createWalls(int numberOfRows, int numberOfColumns) {
		this.walls = new ArrayList<>();
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				if (j != numberOfColumns - 1) {
					walls.add(new Edge(coordinateToId(i, j), coordinateToId(i, j) + 1));
				}
				if (i != numberOfRows - 1) {
					walls.add(new Edge(coordinateToId(i, j), coordinateToId(i, j) + numberOfColumns));
				}
			}
		}
	}

	protected void createLocations(int numberOfRows, int numberOfColumns) {
		this.locations = new Location[n];
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				this.locations[coordinateToId(i, j)] = new Location(coordinateToId(i, j), i, j);
			}
		}
	}

	protected List<Integer> getDistinctRandomNumbers(long numberOfValues) {
		List<Integer> result = new ArrayList<>();
		int tmpIndex;
		while (result.size() < numberOfValues) {
			tmpIndex = random.nextInt(n);
			if (!result.contains(tmpIndex)) {
				result.add(tmpIndex);
			}
		}
		return result;
	}

	protected void buildMaze() {
		int numberOfWallRemoved = 0;
		int wallIndex;
		DisjointSets wallSet = new DisjointSets(n);
		while (numberOfWallRemoved < n - 1) {
			// randomly pick up a wall to see if it can be removed
			wallIndex = random.nextInt(walls.size());
			int x = walls.get(wallIndex).source;
			int y = walls.get(wallIndex).destination;
			// if the start point already has 1 door, don't add another door
			if ((x == startingPoint && locations[x].adjacencyList.size() == 1)
					|| (y == startingPoint && locations[y].adjacencyList.size() == 1)) {
				continue;
			}
			int representativeX = wallSet.findSet(x);
			int representativeY = wallSet.findSet(y);
			if (representativeX != representativeY) {
				wallSet.union(x, y);
				numberOfWallRemoved++;
				locations[x].adjacencyList.add(y);
				locations[y].adjacencyList.add(x);
				walls.remove(wallIndex);
			}
		}
	}

	@Override
	public int getNumberOfRows() {
		return numberOfRows;
	}

	@Override
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	@Override
	public int[] getPlayerCurrentLocation() {
		int[] result = new int[2];
		result[0] = players[turn].currentLocation.rowCoordinate;
		result[1] = players[turn].currentLocation.columnCoordinate;
		return result;
	}

	@Override
	public int getPlayerCurrentLocationId() {
		return players[turn].currentLocation.id;
	}

	@Override
	public int getPlayerCurrentLocationId(int playerId) {
		return players[playerId].currentLocation.id;
	}

	@Override
	public int getPlayerPreviousLocationId() {
		return players[turn].previousLocation != null ? players[turn].previousLocation.id : -1;
	}

	@Override
	public List<Direction> getPossibleMove(int currentId) {
		List<Direction> result = new ArrayList<>();
		for (Integer i : locations[currentId].adjacencyList) {
			result.add(id2Direction(currentId, i));
		}
		return result;
	}

	@Override
	public List<Integer> getAdjacentLocationId(int currentId) {
		return locations[currentId].adjacencyList;
	}

	@Override
	public NodeType getCurrentLocationType() {
		return players[turn].currentLocation.type;
	}

	@Override
	public void move(Direction direction) throws IllegalArgumentException {
		if (!getPossibleMove(players[turn].currentLocation.id).contains(direction)) {
			throw new IllegalArgumentException("Player can't go to that direction");
		}
		int id = getPlayerCurrentLocationId();
		int newId = direction2Id(locations[id], direction);
		players[turn].currentLocation = locations[newId];
		players[turn].previousLocation = locations[id];
	}

	@Override
	public void move(int newId) throws IllegalArgumentException {
		if (newId < 0 || newId > n - 1) {
			throw new IllegalArgumentException("Player can't go to that location");
		}
		int id = getPlayerCurrentLocationId();
		players[turn].currentLocation = locations[newId];
		players[turn].previousLocation = locations[id];
	}

	@Override
	public String movePlayerTo(Direction direction) throws IllegalArgumentException {
		if (!getPossibleMove(players[turn].currentLocation.id).contains(direction)) {
			throw new IllegalArgumentException("Player can't go to that direction");
		}
		StringBuilder output = new StringBuilder();
		int tmpId = nextCave(players[turn].currentLocation.id, direction)[1];
		Location tmpLocation;
		while (true) {
			tmpLocation = locations[tmpId];
			if (tmpLocation.obstacle.get(Obstacle.WUMPUS)) {
				output.append("Chomp, chomp, chomp, thanks for feeding the Wumpus!\n");
				output.append("Better luck next time!\n");
				ifGameEnd = true;
				break;
			} else if (tmpLocation.obstacle.get(Obstacle.PIT)) {
				if (tmpLocation.obstacle.get(Obstacle.BAT)) {
					int chance = random.nextInt(2);
					if (chance == 0) {
						tmpId = pickByBats(tmpId);
						output.append("Snatch -- you are grabbed by superbats and ...\n");
						continue;
					}
				}
				output.append("You encounter a bottomless pit and fall to death!\n");
				output.append("Better luck next time!\n");
				ifGameEnd = true;
				break;
			} else if (tmpLocation.obstacle.get(Obstacle.BAT)) {
				int chance = random.nextInt(2);
				if (chance == 0) {
					tmpId = pickByBats(tmpId);
					output.append("Snatch -- you are grabbed by superbats and ...\n");
				} else {
					output.append("Whoa -- you successfully duck superbats that try to grab you\n");
					break;
				}
			} else {
				break;
			}
		}
		players[0].currentLocation = locations[tmpId];
		return output.toString();
	}

	@Override
	public int encounterBats() {
		int chance = random.nextInt(2);
		int currentId = getPlayerCurrentLocationId();
		if (chance == 0) {
			int newId = random.nextInt(n);
			while (newId == currentId || locations[newId].type != NodeType.CAVE) {
				newId = random.nextInt(n);
			}
			return newId;
		} else {
			return -1;
		}
	}

	protected int pickByBats(int currentId) {
		int newId = currentId;
		while (newId == currentId || locations[newId].type != NodeType.CAVE) {
			newId = random.nextInt(n);
		}
		return newId;
	}

	/**
	 * Return the caves that can be arrived with one move from the location of the
	 * id.
	 *
	 * @param id        current location id
	 * @param direction arrive at a cave with one move from this direction
	 * @return an array, first element is previous location id second element is the
	 *         current cave id
	 */
	protected int[] nextCave(int id, Direction direction) {
		int[] result = new int[2];
		int tmpId = direction2Id(locations[id], direction);
		int previousId = id;
		Location tmpLocation = locations[tmpId];
		while (tmpLocation.type == NodeType.TUNNEL) {
			if (tmpLocation.adjacencyList.get(0) != previousId) {
				previousId = tmpId;
				tmpId = tmpLocation.adjacencyList.get(0);
			} else {
				previousId = tmpId;
				tmpId = tmpLocation.adjacencyList.get(1);
			}
			tmpLocation = locations[tmpId];
		}
		result[0] = previousId;
		result[1] = tmpId;
		return result;
	}

	@Override
	public boolean[] smellAndFeel(int id) {
		if (locations[id].type != NodeType.CAVE) {
			return null;
		}
		boolean[] result = new boolean[2];
		result[0] = false; // smell wumpus
		result[1] = false; // feel draft
		for (Direction direction : getPossibleMove(id)) {
			int smellCave = nextCave(id, direction)[1];
			if (locations[smellCave].obstacle.get(Obstacle.WUMPUS)) {
				result[0] = true;
			}
			if (locations[smellCave].obstacle.get(Obstacle.PIT)) {
				if (!result[1]) {
					result[1] = true;
				}
			}
			if (result[0] && result[1]) {
				break;
			}
		}
		return result;
	}

	@Override
	public String smell(List<Direction> listOfDirection) {
		int id = players[turn].currentLocation.id;
		boolean smellWumpus = false;
		boolean feelDraft = false;
		StringBuilder result = new StringBuilder();
		for (Direction direction : listOfDirection) {
			int smellCave = nextCave(id, direction)[1];
			if (locations[smellCave].obstacle.get(Obstacle.WUMPUS)) {
				smellWumpus = true;
				result.append("You smell a Wumpus!\n");
			}
			if (locations[smellCave].obstacle.get(Obstacle.PIT)) {
				if (!feelDraft) {
					feelDraft = true;
					result.append("You feel a draft!\n");
				}
			}
			if (smellWumpus && feelDraft) {
				break;
			}
		}
		return result.toString();
	}

	@Override
	public boolean getIfGameEnd() {
		return ifGameEnd;
	}

	@Override
	public int[] shoot(Direction direction, int distance) throws IllegalArgumentException {
		// result[0]:
		// 0: run out of arrows
		// 1: hit the wall
		// 2: kill the wumpus
		// 3: didn't got wumpus
		// result[1]: the id of the cell
		int[] result = new int[2];
		if (distance < 1 || distance > 5) {
			throw new IllegalArgumentException("Distance must be in range [1,5]");
		}
		String output = new String();
		if (players[turn].numberOfArrow == 0) {
			players[turn].isAlive = false;
			turn = whoIsNext();
			result[0] = 0;
			return result;
		}
		int[] ids = nextCave(players[turn].currentLocation.id, direction);
		int currentId = ids[1];
		int previousId = ids[0];
		distance--;
		Direction tmpDirection;
		while (distance > 0) {
			tmpDirection = id2Direction(previousId, currentId);
			if (getPossibleMove(currentId).contains(tmpDirection)) {
				ids = nextCave(currentId, tmpDirection);
				previousId = ids[0];
				currentId = ids[1];
				distance--;
			} else {
				result[0] = 1;
				result[1] = currentId;
				break;
			}
		}
		players[turn].numberOfArrow--;
		if (distance == 0) {
			if (locations[currentId].obstacle.get(Obstacle.WUMPUS)) {
				players[turn].isWin = true;
				turn = whoIsNext();
				// You slay wumpus
				result[0] = 2;
				result[1] = currentId;
				return result;
			} else {
				// You didn't got wumpus
				result[0] = 3;
				result[1] = currentId;
			}
		}
		if (players[turn].numberOfArrow == 0) {
			players[turn].isAlive = false;
			// You have insufficient arrows, you are done
			result[0] = 0;
		}
		turn = whoIsNext();
		return result;
	}

	@Override
	public String updateAndReturnGameResult() {
		boolean ifAllDead = true;
		for (int i = 0; i < numberOfPlayers; i++) {
			if (players[i].isWin) {
				ifGameEnd = true;
				return "Player win the game! Next time you won't be so lucky";
			}
			ifAllDead = ifAllDead && (!players[i].isAlive);
		}
		if (ifAllDead) {
			ifGameEnd = true;
			return "player die, Better luck next time";
		}
		return "";
	}

	@Override
	public void setDead() {
		players[turn].isAlive = false;
	}

	protected Direction id2Direction(int from, int to) {
		int moveRowIndex = locations[to].rowCoordinate;
		int moveColumnIndex = locations[to].columnCoordinate;
		int currentRowIndex = locations[from].rowCoordinate;
		int currentColumnIndex = locations[from].columnCoordinate;
		if (currentRowIndex == moveRowIndex) {
			if ((currentColumnIndex + 1) % numberOfColumns == moveColumnIndex) {
				return Direction.EAST;
			}
			if ((moveColumnIndex + 1) % numberOfColumns == currentColumnIndex) {
				return Direction.WEST;
			}
		}
		if (currentColumnIndex == moveColumnIndex) {
			if ((currentRowIndex + 1) % numberOfRows == moveRowIndex) {
				return Direction.SOUTH;
			}
			if ((moveRowIndex + 1) % numberOfRows == currentRowIndex) {
				return Direction.NORTH;
			}
		}
		return null;
	}

	protected int direction2Id(Location current, Direction direction) {
		int newRowIndex = 0;
		int newColumnIndex = 0;
		switch (direction) {
		case EAST:
			newRowIndex = current.rowCoordinate;
			newColumnIndex = (current.columnCoordinate + 1) % numberOfColumns;
			break;
		case WEST:
			newRowIndex = current.rowCoordinate;
			newColumnIndex = (current.columnCoordinate - 1 + numberOfColumns) % numberOfColumns;
			break;
		case SOUTH:
			newRowIndex = (current.rowCoordinate + 1) % numberOfRows;
			newColumnIndex = current.columnCoordinate;
			break;
		case NORTH:
			newRowIndex = (current.rowCoordinate - 1 + numberOfRows) % numberOfRows;
			newColumnIndex = current.columnCoordinate;
			break;
		default:
			break;
		}
		return coordinateToId(newRowIndex, newColumnIndex);
	}

	protected int coordinateToId(int rowIndex, int columnIndex) {
		return rowIndex * numberOfColumns + columnIndex;
	}

	protected void addSideWalls() {
		if (numberOfColumns > 2) {
			for (int i = 0; i < numberOfRows; i++) {
				this.walls.add(new Edge(i * numberOfColumns + numberOfColumns - 1, i * numberOfColumns));
			}
		}
		if (numberOfRows > 2) {
			for (int j = 0; j < numberOfColumns; j++) {
				this.walls.add(new Edge(j + (numberOfRows - 1) * numberOfColumns, j));
			}
		}
	}

	protected void buildNonPerfectMaze() {
		int wallIndex;
		while (walls.size() > numberOfRemainingWalls) {
			// randomly pick up a wall to remove
			wallIndex = random.nextInt(walls.size());
			int x = walls.get(wallIndex).source;
			int y = walls.get(wallIndex).destination;
			if ((x == startingPoint && locations[x].adjacencyList.size() == 1)
					|| (y == startingPoint && locations[y].adjacencyList.size() == 1)) {
				continue;
			}
			locations[x].adjacencyList.add(y);
			locations[y].adjacencyList.add(x);
			walls.remove(wallIndex);
		}
		setLocationType();
		setObstacle(NUMBER_OF_WUMPUS, Obstacle.WUMPUS);
		setObstacle(numberOfPits, Obstacle.PIT);
		setObstacle(numberOfBats, Obstacle.BAT);
	}

	protected void setLocationType() {
		for (Location l : locations) {
			if (l.adjacencyList.size() == 2) {
				l.type = NodeType.TUNNEL;
			} else {
				l.type = NodeType.CAVE;
			}
		}
	}

	protected void setObstacle(int number, Obstacle obstacleName) {
		int count = 0;
		while (count < number) {
			int id = random.nextInt(n);
			if (id != startingPoint && locations[id].type == NodeType.CAVE
					&& !locations[id].obstacle.get(obstacleName)) {
				locations[id].obstacle.put(obstacleName, true);
				count++;
			}
		}
	}

	@Override
	public Map<Obstacle, Boolean> getCurrentDifficulty() {
		return players[turn].currentLocation.obstacle;
	}

	@Override
	public Map<Obstacle, Boolean> getCurrentDifficulty(int id) {
		return locations[id].obstacle;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Walls:");
		for (Edge e : walls) {
			result.append(e.source).append(",").append(e.destination).append("\n");
		}
		for (Location l : locations) {
			result.append("difficulty of cell:").append(l.id).append(": ").append(l.obstacle).append("\n");
			result.append("adjacency list of cell:").append(l.id).append(": ").append(l.adjacencyList).append("\n");
		}
		return result.toString();
	}
}
