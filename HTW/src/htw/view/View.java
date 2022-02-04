package htw.view;

import htw.control.*;
import htw.model.*;
import java.awt.Component;

/**
 * The interface for our view class.
 */
public interface View {
  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param f the set of feature callbacks as a controller object
   */
  void setFeatures(Controller f);

  /**
   * Get the set of feature callbacks that the view can use for maze.
   *
   * @param f the set of feature callbacks as a controller object
   */
  void setMazeFeatures(Controller f);

  /**
   * Show Maze after creating the maze, at the same time make the creation panel invisible.
   */
  void showMaze();

  /**
   * Show welcome page.
   */
  void showWelcome();

  /**
   * Refresh page.
   */
  void refresh();

  /**
   * Refresh status bar.
   */
  void refreshStatusBar();

  /**
   * Set model for view.
   */
  void setModel(ReadonlyModel model);

  /**
   * Remove creation panel from frame.
   */
  void removeCreationPanel();

  /**
   * Remove creation panel from frame.
   */
  void removeMazePanel();

  /**
   * Remove welcome panel from frame.
   */
  void removeWelcomePanel();

  /**
   * Add creation panel to frame.
   */
  void addCreationPanel();

  /**
   * Show message about ducking the bats.
   */
  void duckBats();

  /**
   * Show message about being killed by wumpus.
   */
  void killedByWumpus();

  /**
   * Show message about grabbed by the bats.
   */
  void grabbedByBats();

  /**
   * Show message about falling in the pits.
   */
  void fallInPit();

  /**
   * Show message about shoot result.
   *
   * @param result the result array of shoot
   */
  void showShootResult(int[] shootResult);

  /**
   * Show about dialog.
   */
  void about();

  /**
   * In order to make maze panel respond to keyboard events, it must be within strong focus. Since
   * there could be multiple components on the screen that listen to keyboard events, we must set
   * one as the "currently focussed" one so that all keyboard events are passed to that component.
   * This component is said to have "strong focus".
   * We do this by first making the component focusable and then requesting focus to it. Requesting
   * focus makes the component have focus AND removes focus from whoever had it before.
   */
  void resetFocus();

  /**
   * Show popup menu.
   *
   * @param component the popup menu component
   */
  void showPopup(Component component);

  /**
   * Show dialog ask user select shoot direction.
   *
   * @param options options of directions
   */
  Direction showShootDirectionDialog(Direction[] options);

  /**
   * Show dialog ask user select shoot distance.
   *
   * @param options options of distance
   */
  Integer showShootDistanceDialog(Integer[] options);

  /**
   * Show dialog tell user the game result.
   *
   * @param result result string
   */
  void showGameResult(String result);

  /**
   * Refresh the page after one player is dead.
   */
  void refreshAfterDie();
}