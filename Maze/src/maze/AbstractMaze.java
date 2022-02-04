package maze;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMaze implements Maze {

  private final Player player;

  private final int totalRow;
  private final int totalCol;
  private final int wallNum;
  
  private Location start;
  private Location goal;
  
  private MazeType type;
  private boolean isPerfect;
  private boolean isWrapped;
  
  protected final MazeStructure mazeStructure;
  

  // other variables that are needed for building the maze...
  /**
   * Constructor
   * @throws IllegalArgumentException
   */
  public AbstractMaze(int totalRow, int totalCol, MazeType type, boolean isPerfect, boolean isWrapped, int wallNum)
          throws IllegalArgumentException {
    if (totalRow <= 0 || totalCol <= 0) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    this.totalRow = totalRow;
    this.totalCol = totalCol;
    this.type = type;
    this.isPerfect = isPerfect;
    this.isWrapped = isWrapped;
    
    if ((isWrapped && wallNum > calculateWallNum())
            || (!isWrapped
            && wallNum > calculateWallNum())) {
      throw new IllegalArgumentException(" Number of remaining walls not possible.");
    }
    
    this.wallNum = wallNum;
    this.mazeStructure = new MazeStructure(totalRow, totalCol, isPerfect, isWrapped, wallNum);
    this.player = new Player();
  }
  
  private int calculateWallNum() {
	  if (type == MazeType.WrappingRoom) {
		  return 2 * (totalRow + 1) * (totalCol + 1 )  - (totalRow + 1)
          - (totalCol + 1 ) - (totalRow * totalCol)  + 1;
	  }
	  else {
		  return totalRow * totalCol  - totalRow
		            - totalCol + 1;
	  }
  }
  
  @Override
  public int getRowNum() {
    return totalRow;
  }

  @Override
  public int getColumnNum() {
    return totalCol;
  }

  @Override
  public int getWallNum() {
    return wallNum;
  }
  
  @Override
  public void setPlayer(Player player, Location loc) {
	int row = loc.getRow();
	int col = loc.getCol();
    if (row < 0 || row >= totalRow || col < 0 || col >= totalCol) {
      throw new IllegalArgumentException("Starting point invalid.");
    }
    start = loc;
    player.setLoc(loc);
  }
  
  @Override
  public void setGoal(Location goal) throws IllegalArgumentException {
	int row = goal.getRow();
	int col = goal.getCol();	  
	  
    if (row < 0 || row >= totalRow || col < 0 || col >= totalCol) {
      throw new IllegalArgumentException("Goal point invalid.");
    }

    if (goal.equals(start)) {
      throw  new IllegalArgumentException("Cannot be same as starting point.");
    }
    
    this.goal = goal;
  }
  
  @Override
  public Location getPlayerLoc() {
	  return player.getLoc();
  }
  
  @Override
  public int getGoldCount() {
	  return player.getCoins();
  }
  
  @Override
  public Set<Direction> getPossibleMoves(){
	Location loc = getPlayerLoc();
	
	int row = loc.getRow();
	int col = loc.getCol();
	
	if (row >= totalRow || row < 0 || col >= totalCol || col < 0 ) {
	      throw new IllegalArgumentException("Invalid location.");
    }
	
    List<Location> list = mazeStructure.getAdjList().get(loc);
    Set<Direction> directions = new HashSet<>();
    Location node;

    //North
    if (isWrapped) {
      node = new Location((row - 1 + totalRow) % totalRow, col);
    }
    else {
      node = new Location((row - 1 ), col);
    }
    if (list.contains(node)) {
      directions.add(Direction.NORTH);
    }

    //South
    if (isWrapped) {
      node = new Location((row + 1 + totalRow) % totalRow, col);
    }
    else {
      node = new Location((row + 1), col);
    }
    if (list.contains(node)) {
      directions.add(Direction.SOUTH);
    }

    //East
    if (isWrapped) {
      node = new Location(row, (col + 1 + totalCol) % totalCol);
    }
    else {
      node = new Location(row, (col + 1));
    }
    if (list.contains(node)) {
      directions.add(Direction.EAST);
    }


    //West
    if (isWrapped) {
      node = new Location(row, (col - 1 + totalCol) % totalCol);
    }
    else {
      node = new Location(row, (col - 1) );
    }
    if (list.contains(node)) {
      directions.add(Direction.WEST);
    }

    return directions;
  }


  @Override
  public void move(Direction direction) {
	  if (!getPossibleMoves().contains(direction)) {
	      throw new IllegalArgumentException("Invalid move.");
	    }

	    Location currentLoc = player.getLoc();
	    int currentRow = currentLoc.getRow();
	    int currentCol = currentLoc.getCol();
	    
	    Location newLocation = null;

	    switch (direction) {
	      case EAST:
	        newLocation = new Location(currentRow,
	                (currentCol + 1 + totalCol) % totalCol);
	        break;
	      case WEST:
	        newLocation = new Location(currentRow,
	                (currentCol - 1 + totalCol) % totalCol);
	        break;
	      case NORTH:
	        newLocation = new Location((currentRow - 1 + totalRow)
	                % totalRow, currentCol);
	        break;
	      case SOUTH:
	        newLocation = new Location((currentRow + 1 + totalRow)
	                % totalRow, currentCol);
	        break;
	      default: throw new IllegalArgumentException("Invalid action.");
	    }
	    Map<Location, NodeType> NodeMap = mazeStructure.getNodeTypeList();
	    NodeType Node = NodeMap.get(newLocation);
	    if (Node.equals(Node.GOLD)) {
	      player.pickupCoins(1111111);;
	      NodeMap.replace(newLocation, Node.BLANK);
	    }
	    else if (Node.equals(Node.THIEF)) {
	      player.loseCoins();
	    }

	    player.setLoc(newLocation);
  }
  
  @Override
  public String toString() {
    return "AbstractMaze\n"
            + "totalRow=" + totalRow + "\n"
            + "totalCol=" + totalCol + "\n"
            +  "isPecfect=" + isPerfect + "\n"
            +  "isWrapped=" + isWrapped + "\n"
            +  "wallNum=" + wallNum + "\n"
            + "startingPoint=" + start.getRow() +","  + ", goalPoint=" + goal+ goal.getRow() + "\n" 
            + "mazeStructure=" + mazeStructure.getAdjList().toString();
  }

  // implement those defined in Maze, also a few more methods
  // that for building the maze
}
