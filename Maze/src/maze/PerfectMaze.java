package maze;

public class PerfectMaze extends AbstractMaze {
  // seems not much to be put here, maybe just a constructor? It's up to you.
  // But it's good to have this hierarchical structure of different mazes for
	
  /**
   * Constructor for Perfect Room Maze.
   * */
  public PerfectMaze(int totalRow, int totalCol) {
    super(totalRow, totalCol, MazeType.Perfect, true, false, totalRow * totalCol  - totalRow - totalCol + 1);
  }
}
