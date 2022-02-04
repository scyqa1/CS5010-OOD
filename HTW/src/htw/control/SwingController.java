package htw.control;

import htw.model.*;
import htw.view.View;

import java.awt.Component;

/**
 * The controller now implements the Controller interface for swing view and model. This means each
 * of those functions will give control to the controller.
 */
public class SwingController implements Controller {
  private View view;
  private Maze model;

  // arguments used to construct maze
  private int numberOfPlayers = 1;
  private String wrappingType;
  private int numberOfRows;
  private int numberOfColumns;
  private int startingPoint;
  private int numberOfPits;
  private int numberOfBats;
  private int numberOfArrow;
  private long seed;
  private int numberOfRemainingWalls;

  /**
   * Constructor for swing controller.
   *
   * @param view the view to use
   */
  public SwingController(View view) {
    this.view = view;
  }

  @Override
  public void start() {
    view.setFeatures(this);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void stopGame() {
    view.removeMazePanel();
    view.showWelcome();
  }

  @Override
  public void restartNewGame() {
    view.removeWelcomePanel();
    view.addCreationPanel();
  }

  @Override
  public void restartSameGame() {
    view.removeWelcomePanel();
    constructMaze();
    view.setModel(model);
    view.showMaze();
    view.setMazeFeatures(this);
    view.resetFocus();
  }

  private void processMazeArguments(String numberOfPlayers, String wrappingType,
                                    String numberOfRows, String numberOfColumns,
                                    String startingPoint, String numberOfPits,
                                    String numberOfBats, String numberOfArrow,
                                    String seed, String numberOfRemainingWalls)
          throws IllegalArgumentException {
    if (numberOfPlayers.isEmpty()) {
      numberOfPlayers = "1";
    }
    if (wrappingType.isEmpty()) {
      wrappingType = "wrapping";
    }
    if (numberOfRows.isEmpty()) {
      numberOfRows = "4";
    }
    if (numberOfColumns.isEmpty()) {
      numberOfColumns = "6";
    }
    if (startingPoint.isEmpty()) {
      startingPoint = "18";
    }
    if (numberOfPits.isEmpty()) {
      numberOfPits = "1";
    }
    if (numberOfBats.isEmpty()) {
      numberOfBats = "2";
    }
    if (numberOfArrow.isEmpty()) {
      numberOfArrow = "3";
    }
    if (seed.isEmpty()) {
      seed = "6519823";
    }
    if (numberOfRemainingWalls.isEmpty()) {
      numberOfRemainingWalls = "10";
    }
    if (!isInteger(numberOfRows) || !isInteger(numberOfColumns) ) {
      throw new IllegalArgumentException("Input must be integer");
    }
    this.numberOfPlayers = Integer.parseInt(numberOfPlayers);
    this.wrappingType = wrappingType;
    this.numberOfRows = Integer.parseInt(numberOfRows);
    this.numberOfColumns = Integer.parseInt(numberOfColumns);
    this.startingPoint = Integer.parseInt(startingPoint);
    this.numberOfPits = Integer.parseInt(numberOfPits);
    this.numberOfBats = Integer.parseInt(numberOfBats);
    this.numberOfArrow = Integer.parseInt(numberOfArrow);
    this.seed = Long.parseLong(seed);
    this.numberOfRemainingWalls = Integer.parseInt(numberOfRemainingWalls);
  }

  private void constructMaze() {
    if (wrappingType.equals("non-wrapping")) {
      // create the model
      this.model = new NonPerfectMaze(numberOfRows, numberOfColumns, startingPoint,
              numberOfPits, numberOfBats, numberOfArrow, seed, numberOfPlayers,
              numberOfRemainingWalls
      );
    }
    if (wrappingType.equals("wrapping")) {
      // create the model
      this.model = new WrappingNonPerfectMaze(numberOfRows, numberOfColumns, startingPoint,
              numberOfPits, numberOfBats, numberOfArrow, seed, numberOfPlayers,
              numberOfRemainingWalls
      );
    }
  }

  @Override
  public void createMaze(String numberOfPlayers, String wrappingType,
                         String numberOfRows, String numberOfColumns, String startingPoint,
                         String numberOfPits, String numberOfBats, String numberOfArrow,
                         String seed, String numberOfRemainingWalls) {
    try {
      processMazeArguments(numberOfPlayers, wrappingType, numberOfRows, numberOfColumns,
              startingPoint, numberOfPits, numberOfBats, numberOfArrow,
              seed, numberOfRemainingWalls);
    } catch (IllegalArgumentException e) {
      return;
    }
    constructMaze();
    view.setModel(model);
    view.removeCreationPanel();
    view.showMaze();
    view.setMazeFeatures(this);
    view.resetFocus();
  }

  /**
   * After move, check whether need to move again or game over.
   */
  private void afterMove() {
    view.refresh();
    view.resetFocus();
    if (model.getCurrentDifficulty().get(Obstacle.BAT)) {
      int resultBats = model.encounterBats();
      if (resultBats == -1) {
        view.duckBats();
      } else {
        view.grabbedByBats();
        move(resultBats);
      }
    }
    if (model.getCurrentDifficulty().get(Obstacle.WUMPUS)) {
      model.setDead();
      view.killedByWumpus();
      view.refreshAfterDie();
      return;
    }
    if (model.getCurrentDifficulty().get(Obstacle.PIT)) {
      model.setDead();
      view.fallInPit();
      view.refreshAfterDie();
    }
  }

  @Override
  public void move(Direction direction) {
    try {
      model.move(direction);
    } catch (IllegalArgumentException e) {
      return;
    }
    afterMove();
    model.switchTurn(model.whoIsNext());
    view.refreshStatusBar();
    String gameResult = model.updateAndReturnGameResult();
    if (!gameResult.isEmpty()) {
      view.showGameResult(gameResult);
    }
    if (model.getIfGameEnd()) {
      stopGame();
    }
  }

  @Override
  public void move(int id) {
    try {
      model.move(id);
    } catch (IllegalArgumentException e) {
      return;
    }
    afterMove();
  }

  @Override
  public void processClick(int id) {
    if (model.getAdjacentLocationId(model.getPlayerCurrentLocationId()).contains(id)) {
      move(id);
      model.switchTurn(model.whoIsNext());
      view.refreshStatusBar();
      String gameResult = model.updateAndReturnGameResult();
      if (!gameResult.isEmpty()) {
        view.showGameResult(gameResult);
      }
      if (model.getIfGameEnd()) {
        stopGame();
      }
    }
  }

  @Override
  public void showAbout() {
    view.about();
  }

  @Override
  public void processPopup(Component component, int id) {
    if (id == model.getPlayerCurrentLocationId()
            && model.getCurrentLocationType() == NodeType.CAVE) {
      view.showPopup(component);
    }
  }

  @Override
  public void shoot() {
    Direction[] directionOptions = model.getPossibleMove(model.getPlayerCurrentLocationId())
            .toArray(new Direction[0]);
    Direction selectedDirection = view.showShootDirectionDialog(directionOptions);
    if (selectedDirection == null) {
      return;
    }
    Integer[] distanceOptions = {1, 2, 3, 4, 5};
    Integer selectedDistance = view.showShootDistanceDialog(distanceOptions);
    if (selectedDistance == null) {
      return;
    }
    int[] result = model.shoot(selectedDirection, selectedDistance);
    String gameResult = model.updateAndReturnGameResult();
    view.showShootResult(result);
    view.refreshStatusBar();
    if (!gameResult.isEmpty()) {
      view.showGameResult(gameResult);
    }
    if (model.getIfGameEnd()) {
      stopGame();
    }
  }

  @Override
  public void cancelCreation() {
    view.removeCreationPanel();
    view.showWelcome();
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