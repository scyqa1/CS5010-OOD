package maze;

public class Driver {
    public static void main(String[] args) {
        int numOfRows = 4;
        int numOfColumns = 6;
        Location start = new Location(0, 0);
        Location goal = new Location(1, 1);

        // perfect maze
        Maze perfectMaze = new PerfectMaze(numOfRows, numOfColumns);
        Player aqc = new Player();
		perfectMaze.setPlayer(aqc, start);
		perfectMaze.setGoal(goal);
        Direction[] directions1 = {
                Direction.EAST,
                Direction.SOUTH,
                Direction.SOUTH,
                Direction.WEST, // meet thief
                Direction.EAST,
                Direction.SOUTH,
                Direction.EAST,
                Direction.NORTH, // collect coin
                Direction.SOUTH,
                Direction.EAST,
                Direction.EAST,
                Direction.NORTH, // collect coin
                Direction.WEST,
                Direction.NORTH,
                Direction.EAST, // meet thief
                Direction.WEST,
                Direction.SOUTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.EAST}; // reach goal
        System.out.println(perfectMaze.toString());
        //perfectMaze.move(Direction.EAST);

        // wrapping room maze
        Maze wrappingMaze = new WrappingRoomMaze(numOfRows, numOfColumns, 3);
        Player p2 = new Player();
        wrappingMaze.setPlayer(p2,start);
        Direction[] directions2 = {
                Direction.EAST,
                Direction.EAST,
                Direction.EAST, // collect coin
                Direction.EAST, // collect coin
                Direction.SOUTH, // meet thief
                Direction.NORTH,
                Direction.NORTH, // move to the bottom
                Direction.EAST}; // reach goal
        wrappingMaze.toString();
        //wrappingMaze.move(Direction.EAST);
    }
}
