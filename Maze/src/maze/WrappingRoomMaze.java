package maze;

public class WrappingRoomMaze extends RoomMaze {

  /**
   * Constructor for Perfect Room Maze.
   * */
  public WrappingRoomMaze(int totalRow, int totalCol, int wallNum) {
    super(totalRow, totalCol, MazeType.WrappingRoom, true, wallNum);
  }
  
}
