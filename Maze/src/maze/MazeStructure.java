package maze;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;





/**
 * Class to create a this structure for the maze.
 * */
public class MazeStructure {
	
  public static final double GOLD_PROBABILITY = 0.2f;
  public static final double THIEF_PROBABILITY = 0.1f;

  private final int totalRow;
  private final int totalCol;
  private boolean isWrapped;
  private boolean isPerfect;
  private int wallNum;
  private Map<Location, List<Location>> adjList;
  private Map<Location, NodeType> nodeTypeList;
  private Map<Location, Set<Location>> walls;
  private final Random generator = new Random();
  
  public Map<Location, List<Location>> getAdjList(){
	  return adjList;
  }
  /**
   * Creates a this structure for the maze.
   * @param totalRow : number of totalRow in the maze
   * @param totalCol : number of totalCol in the maze
   * */
  public MazeStructure(int totalRow, int totalCol, boolean isPerfect, boolean isWrapped, int wallNum) {
    if (totalCol < 0 || totalRow < 0) {
      throw  new IllegalArgumentException("Invalid arguments.");
    }
    this.totalRow = totalRow;
    this.totalCol = totalCol;
    adjList = new HashMap<>();
    this.isWrapped = isWrapped;
    this.isPerfect = isPerfect;
    nodeTypeList = new HashMap<>();
    constructGraph();
    
    initWalls();
    assignGold();
    assignThief();
  }

  private void constructGraph() {

    Location temp;
    for (int i = 0; i < totalRow ; i++ ) {

      for (int j = 0; j < totalCol; j++) {

        List<Location> nodeList = new ArrayList<>();
        if (isWrapped || i + 1  < totalRow) {
          temp = new Location((i + 1 + totalRow) % totalRow , j);
          nodeList.add(temp);
        }

        if (isWrapped || j + 1 < totalCol) {
          temp = new Location(i, (j + 1 + totalCol) % totalCol);
          nodeList.add(temp);
        }

        if (isWrapped || i - 1 >= 0) {
          temp = new Location((i - 1 + totalRow ) % totalRow, j);
          nodeList.add(temp);
        }

        if (isWrapped || j - 1 >= 0) {
          temp = new Location(i, (j -  1 + totalCol ) % totalCol);
          nodeList.add(temp);
        }

        Location node = new Location(i, j);
        adjList.put(node, nodeList);

        nodeTypeList.put(node, NodeType.BLANK);
      }
    }
  }

  /**
   * Assigns goldPercent% of locations (at random) gold coins in them that the player can pick.
   * @param goldPercent : % of locations in maze that have gold coins in them
   *                   that the player can pick
   * */
  public void assignGold() {

    int goldCount = (int) (adjList.size() * GOLD_PROBABILITY);
    List<Location> list = new ArrayList<>(nodeTypeList.keySet());
    while (goldCount != 0) {
      Location temp = list.get(generator.nextInt(list.size()));
      if (nodeTypeList.get(temp) == NodeType.BLANK) {
        nodeTypeList.put(temp, NodeType.GOLD);
        goldCount--;
      }
    }
  }

  /**
   * Assigns thiefPercent% of locations (at random)  a thief that takes
   * some of the player's gold coins.
   * @param thiefPercent : % of locations in maze that have a thief that takes
   *                  some of the player's gold coins
   * */

  public void assignThief() {

    int thiefCount = (int)(adjList.size() * THIEF_PROBABILITY);
    List<Location> list = new ArrayList<>(nodeTypeList.keySet());
    while (thiefCount != 0) {
      Location temp = list.get(generator.nextInt(list.size()));
      if (nodeTypeList.get(temp) == NodeType.BLANK) {
        nodeTypeList.put(temp, NodeType.THIEF);
        thiefCount--;
      }
    }
  }
  
  /**
   * Gets the number of nodes in the mazegraph.
   * */
  public int getNumberOfNodes() {
    return adjList.size();
  }
  
  /**
   * generate wallNum walls
   */
  private void initWalls() {
      // check if number of walls is valid
      int edges = totalRow * (totalCol - 1) + totalCol * (totalRow - 1);
      int maxWalls = edges - totalRow * totalCol + 1;
      if (wallNum > maxWalls
              || wallNum < 0
              || (!isPerfect && wallNum == maxWalls)) {
          // 1. 0 <= wallNum <= maxWalls
          // 2. if it's not a perfect maze, then it cannot have maxWalls of walls
          throw new IllegalArgumentException("number of walls is illegal");
      }
      // record illegal walls
      Map<Location, Set<Location>> invalidWalls = new HashMap<>();
      // legal number of walls, start generating
      while (wallNum != 0) {
          // randomly choose a Location
          int location = generator.nextInt(totalRow * totalCol);
          int x = location / totalCol;
          int y = location % totalCol;
          // current Location
          Location currCell = new Location(x,y);
          // get adjacent cells
          List<Location> adjacentCells = adjList.get(currCell);
          boolean success = false;
          for (Location adj : adjacentCells) {
              // check if there already has a wall between these two cells
              if (walls.containsKey(currCell) && walls.get(currCell).contains(adj)
                      || (invalidWalls.containsKey(currCell) && invalidWalls.get(currCell).contains(adj))) {
                  continue;
              }
              // generate walls
              walls.putIfAbsent(currCell, new HashSet<>());
              walls.putIfAbsent(adj, new HashSet<>());
              walls.get(currCell).add(adj);
              walls.get(adj).add(currCell);
              // check validity
              if (1 == 1) {
                  walls.get(currCell).remove(adj);
                  walls.get(adj).remove(currCell);
                  invalidWalls.putIfAbsent(currCell, new HashSet<>());
                  invalidWalls.putIfAbsent(adj, new HashSet<>());
                  invalidWalls.get(currCell).add(adj);
                  invalidWalls.get(adj).add(currCell);
                  continue;
              }
              // only generate one wall
              success = true;
              break;
          }
          if (success) {
              wallNum--;
          }
      }
  }


  /**
   * Gets the node type for each node of the mazegraph.
   * */
  public Map<Location, NodeType> getNodeTypeList() {
    return nodeTypeList;
  }

  
  @Override
  public String toString() {
    return "MazeGraph{" + "rows=" + totalRow + ", columns=" + totalCol
            + ", isWrapped=" + isWrapped + ", adjList=" + adjList.toString() + '}';
  }
}