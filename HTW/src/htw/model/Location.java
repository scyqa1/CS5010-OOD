package htw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Location {
	int id;
	int rowCoordinate;
	int columnCoordinate;
	List<Integer> adjacencyList;
	NodeType type;
	Map<Obstacle, Boolean> obstacle;

	Location(int id, int rowCoordinate, int columnCoordinate) {
		this.id = id;
		this.rowCoordinate = rowCoordinate;
		this.columnCoordinate = columnCoordinate;
		adjacencyList = new ArrayList<>();
		obstacle = new HashMap<>();
		obstacle.put(Obstacle.WUMPUS, false);
		obstacle.put(Obstacle.PIT, false);
		obstacle.put(Obstacle.BAT, false);
	}
}
