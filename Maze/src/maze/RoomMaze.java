package maze;

public class RoomMaze extends AbstractMaze {
  // see the comment in PerfectMaze

  /**
   * Constructor for Perfect Room Maze.
   * */
  public RoomMaze(int totalRow, int totalCol, MazeType type, boolean iswrapped, int wallNum) {
    super(totalRow, totalCol, type, false, iswrapped, wallNum);
  }

}
