package htw.view;

import htw.control.Controller;
import htw.model.ReadonlyModel;
import htw.model.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

/**
 * Implementation of the view.
 */
public class Swing extends JFrame implements View {
  private ReadonlyModel model;
  // creation page
  private JPanel creationPanel;
  private JButton createButton;
  private JButton cancelButton;
  private JTextField[] textFields;
  private JComboBox boxPlayerNumber;
  private JComboBox boxWrappingType;
  private BufferedImage[] background;
  private boolean[] explored;
  // maze page
  private JPanel mazePanel;
  private JLabel[] mazeLabels;
  private static final String IMAGE_PATH = "/img/";
  private static final String BLACK_FILE_NAME = "black.png";
  private static final String[] PLAYERS_FILE_NAME = {"player1.png", "player2.png"};
  private static final String BAT_FILE_NAME = "bats.png";
  private static final String PIT_FILE_NAME = "pit.png";
  private static final String WUMPUS_FILE_NAME = "wumpus.png";
  private static final String STENCH_FILE_NAME = "stench.png";
  private static final String BREEZE_FILE_NAME = "breeze.png";
  private static final String WELCOME_FILE_NAME = "huntwumpus.png";
  private static final String ARROW_FILE_NAME = "arrow.png";
  private static final String ARROWS_FILE_NAME = "arrows.png";
  private static final String ARROW1_FILE_NAME = "arrow1.png";
  private static final String[] LABELS = {"Number of Players: ", "Wrapping Type: ",
      "Number Of Rows: ", "Number Of Columns: ", "Starting Point: ",
      "Number Of Pits: ", "Number Of Bats: ",
      "Number Of Arrow: ", "Seed: ", "Number Of RemainingWalls: "};
  private static final String[] DEFAULT_VALUES = {"1", "non-wrapping", "4", "6", "18",
      "1", "2", "3", "570834998", "10"};
  private static final String[] WRAPPING_TYPE = {"non-wrapping", "wrapping"};
  private static final String[] PLAYER_NUMBER = {"1", "2"};
  // status bar
  private JPanel statusBar;
  private JLabel playerIdLabel;
  private JLabel numberOfArrowLabel;
  private JLabel playerImageLabel;
  private JLabel arrowsImageLabel;

  // menu
  private JMenuItem exit;
  private JMenuItem stopGame;
  private JMenuItem restartSame;
  private JMenuItem restartNew;
  private JMenuItem about;

  // popup menu
  private JPopupMenu popup;
  private JMenuItem shoot;

  // welcome page
  private JPanel welcomePanel;

  /**
   * Constructor of the swing view.
   *
   * @param windowTitle title of the window
   */
  public Swing(String windowTitle) {
    super(windowTitle);
    setSize(5000, 3000);
    setLocation(350, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // initialize menu bar
    JMenuBar menuBar = new JMenuBar();
    JMenu action = new JMenu("Exit");
    action.setMnemonic(KeyEvent.VK_F);
    menuBar.add(action);
    this.exit = new JMenuItem("Exit", KeyEvent.VK_X);
    action.add(exit);
    JMenu restart = new JMenu("Game");
    restart.setMnemonic(KeyEvent.VK_G);
    menuBar.add(restart);
    this.stopGame = new JMenuItem("Stop", KeyEvent.VK_P);
    restart.add(stopGame);
    this.restartSame = new JMenuItem("Restart", KeyEvent.VK_S);
    restart.add(restartSame);
    this.restartNew = new JMenuItem("Start", KeyEvent.VK_N);
    restart.add(restartNew);

    this.setJMenuBar(menuBar);

    // initialize popup
    this.popup = new JPopupMenu();
    this.shoot = new JMenuItem("Shoot", KeyEvent.VK_S);
    popup.add(shoot);

    // Create and show welcome panel
    try {
      URL imageUrl = getClass().getResource(IMAGE_PATH + WELCOME_FILE_NAME);
      this.welcomePanel = new JPanel();
      JLabel welcomeLabel = new JLabel(new ImageIcon(imageUrl));
      welcomePanel.add(welcomeLabel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    showWelcome();
    // Create and populate the panel with text fields.
    JPanel panel1 = new JPanel(new SpringLayout());
    int numPairs = LABELS.length;
    JLabel labelPlayerNumber = new JLabel(LABELS[0], JLabel.TRAILING);
    panel1.add(labelPlayerNumber);
    boxPlayerNumber = new JComboBox(PLAYER_NUMBER);
    boxPlayerNumber.setSelectedIndex(0);
    labelPlayerNumber.setLabelFor(boxPlayerNumber);
    panel1.add(boxPlayerNumber);

    JLabel labelWrappingType = new JLabel(LABELS[1], JLabel.TRAILING);
    panel1.add(labelWrappingType);
    boxWrappingType = new JComboBox(WRAPPING_TYPE);
    boxWrappingType.setSelectedIndex(0);
    labelWrappingType.setLabelFor(boxWrappingType);
    panel1.add(boxWrappingType);

    this.textFields = new JTextField[numPairs];
    for (int i = 2; i < numPairs; i++) {
      JLabel l = new JLabel(LABELS[i], JLabel.TRAILING);
      panel1.add(l);
      JTextField textField = new JTextField(DEFAULT_VALUES[i], 20);
      l.setLabelFor(textField);
      textFields[i] = textField;
      panel1.add(textField);
    }
    //Lay out the panel.
    SpringUtilities.makeCompactGrid(panel1,
            numPairs, 2, //rows, cols
            10, 10,        //initX, initY
            10, 5);       //xPad, yPad
    JPanel panel2 = new JPanel();
    // create button
    createButton = new JButton("Create");
    createButton.setActionCommand("Create Button");
    cancelButton = new JButton("Cancel");
    panel2.add(createButton);
    panel2.add(cancelButton);
    panel2.setBorder(BorderFactory.createEmptyBorder(0, 0,
            10, 0));
    // creation panel
    creationPanel = new JPanel() {
      //Don't allow us to stretch vertically.
      public Dimension getMaximumSize() {
        Dimension pref = getPreferredSize();
        return new Dimension(Integer.MAX_VALUE, pref.height);
      }
    };
    creationPanel.setLayout(new BoxLayout(creationPanel, BoxLayout.PAGE_AXIS));
    creationPanel.add(panel1);
    creationPanel.add(panel2);
    pack();
    setVisible(true);
  }

  public void setModel(ReadonlyModel model) {
    this.model = model;
  }

  @Override
  public void resetFocus() {
    mazePanel.setFocusable(true);
    mazePanel.requestFocus();
  }

  @Override
  public void removeWelcomePanel() {
    this.remove(welcomePanel);
    restartSame.setEnabled(false);
    restartNew.setEnabled(false);
  }

  @Override
  public void removeCreationPanel() {
    this.remove(creationPanel);
  }

  @Override
  public void removeMazePanel() {
    this.remove(mazePanel);
    this.remove(statusBar);
  }

  @Override
  public void addCreationPanel() {
    this.add(creationPanel);
    pack();
  }

  @Override
  public void duckBats() {
    JOptionPane.showMessageDialog(this,
            "Whoa -- you successfully duck superbats that try to grab you!");
  }

  @Override
  public void killedByWumpus() {
    JOptionPane.showMessageDialog(this,
            "Chomp, chomp, chomp, thanks for feeding the Wumpus!\n");
  }

  @Override
  public void grabbedByBats() {
    JOptionPane.showMessageDialog(this,
            "Snatch -- you are grabbed by superbats and ...!");
  }

  @Override
  public void fallInPit() {
    JOptionPane.showMessageDialog(this,
            "You encounter a bottomless pit and fall to death!\n");
  }

  @Override
  public void showShootResult(int[] shootResult) {
    String input = new String();
    if (shootResult[0] == 0) {
      input = "You run out of arrows! You are done!";
    } else if (shootResult[0] == 1) {
      input = "Arrow hit the wall at location " + shootResult[1];
    } else if (shootResult[0] == 2) {
      input = "Hee hee hee, you killed the wumpus at cave " + shootResult[1];
      if (!explored[shootResult[1]]) {
        refreshUnexploredBackground(shootResult[1]);
      }
      BufferedImage currentImage = background[shootResult[1]];
      try {
        //currentImage = overlay(currentImage, IMAGE_PATH + BLOOD_FILE_NAME, 10);
        currentImage = overlay(currentImage, IMAGE_PATH + ARROW1_FILE_NAME,
                -2);
      } catch (IOException e) {
        e.printStackTrace();
      }
      mazeLabels[shootResult[1]].setIcon(new ImageIcon(currentImage));
      mazePanel.repaint();
    } else if (shootResult[0] == 3) {
      input = "Unfortunately, you didn't got the wumpus at location " + shootResult[1];
    }
    JOptionPane.showMessageDialog(this, input);
  }

  @Override
  public void showGameResult(String result) {
    JOptionPane.showMessageDialog(this, result);
  }

  @Override
  public void about() {
    JOptionPane.showMessageDialog(this,
            "Hunt the Wumpus 2020.12\n\n\n     copyright Qiang Fang");
  }

  @Override
  public void showMaze() {
    stopGame.setEnabled(true);
    // initialize and add maze panel
    int numberOfRows = model.getNumberOfRows();
    int numberOfColumns = model.getNumberOfColumns();
    GridLayout mazeLayout = new GridLayout(numberOfRows, numberOfColumns);
    this.mazePanel = new JPanel(mazeLayout);
    resetFocus();
    int totalCells = numberOfRows * numberOfColumns;
    this.mazeLabels = new JLabel[totalCells];
    this.background = new BufferedImage[totalCells];
    this.explored = new boolean[totalCells];
    Arrays.fill(explored, false);
    for (int i = 0; i < totalCells; i++) {
      mazeLabels[i] = new JLabel();
      mazePanel.add(mazeLabels[i]);
      try {
        URL imageUrl = getClass().getResource(IMAGE_PATH + BLACK_FILE_NAME);
        //Image myPicture = Toolkit.getDefaultToolkit().getImage(imageUrl);
        mazeLabels[i].setIcon(new ImageIcon(imageUrl));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    this.add(mazePanel, BorderLayout.CENTER);
    mazePanel.setVisible(true);

    // create and show status bar
    statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    playerImageLabel = new JLabel();
    arrowsImageLabel = new JLabel();
    playerIdLabel = new JLabel();
    numberOfArrowLabel = new JLabel();
    try {
      URL imageUrl = getClass().getResource(IMAGE_PATH + ARROWS_FILE_NAME);
      arrowsImageLabel.setIcon(new ImageIcon(imageUrl));
    } catch (Exception e) {
      e.printStackTrace();
    }
    statusBar.add(playerImageLabel);
    statusBar.add(playerIdLabel);
    statusBar.add(arrowsImageLabel);
    statusBar.add(numberOfArrowLabel);
    this.add(statusBar, BorderLayout.PAGE_END);
    statusBar.setVisible(true);
    refreshStatusBar();

    // refresh the background of starting point
    refreshUnexploredBackground(model.getStartingPoint());
    // add all players in starting point
    int currentId = model.getPlayerCurrentLocationId();
    BufferedImage currentImage = background[currentId];
    try {
      for (int i = 0; i < model.getNumberOfPlayers(); i++) {
        currentImage = overlay(currentImage, IMAGE_PATH + PLAYERS_FILE_NAME[i],
                i * 10 + 5);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    mazeLabels[currentId].setIcon(new ImageIcon(currentImage));
    mazePanel.repaint();
    pack();
  }

  @Override
  public void refreshStatusBar() {
    try {
      URL imageUrl = getClass().getResource(IMAGE_PATH
              + PLAYERS_FILE_NAME[model.getTurn()]);
      playerImageLabel.setIcon(new ImageIcon(imageUrl));
    } catch (Exception e) {
      e.printStackTrace();
    }
    playerIdLabel.setText((model.getTurn() + 1) + "  ");
    numberOfArrowLabel.setText(String.valueOf(model.getNumberOfArrows()));
    statusBar.repaint();
  }

  @Override
  public void showWelcome() {
    this.add(welcomePanel);
    restartNew.setEnabled(true);
    if (this.model != null) {
      restartSame.setEnabled(true);
    } else {
      restartSame.setEnabled(false);
    }
    stopGame.setEnabled(false);
    pack();
  }

  @Override
  public void showPopup(Component component) {
    popup.show(component, 25, 15);
  }

  @Override
  public Direction showShootDirectionDialog(Direction[] options) {
    URL imageUrl = getClass().getResource(IMAGE_PATH + ARROW_FILE_NAME);
    ImageIcon icon = new ImageIcon(imageUrl);
    return  (Direction) JOptionPane.showInputDialog(null,
            "Select a direction to shoot",
            "Shoot direction", JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
  }

  @Override
  public Integer showShootDistanceDialog(Integer[] options) {
    URL imageUrl = getClass().getResource(IMAGE_PATH + ARROW_FILE_NAME);
    ImageIcon icon = new ImageIcon(imageUrl);
    return  (Integer) JOptionPane.showInputDialog(null,
            "Select a distance to shoot",
            "Shoot distance", JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
  }

  private void refreshUnexploredBackground(int currentId) {
    List<Direction> directions = model.getPossibleMove(currentId);
    String result = directions.stream().map(a -> a.toString().substring(0, 1)).sorted()
            .collect(Collectors.joining());
    String cellFileName = IMAGE_PATH + result + ".png";
    URL backImageUrl = getClass().getResource(cellFileName);
    try {
      BufferedImage backgroundImage = ImageIO.read(backImageUrl);
      Map<Obstacle, Boolean> difficulty = model.getCurrentDifficulty(currentId);
      boolean[] smell = model.smellAndFeel(currentId);
      if (smell != null) {
        if (smell[0]) {
          backgroundImage = overlay(backgroundImage, IMAGE_PATH + STENCH_FILE_NAME, 0);
        }
      }
      if (difficulty.get(Obstacle.WUMPUS)) {
        backgroundImage = overlay(backgroundImage, IMAGE_PATH + WUMPUS_FILE_NAME, 6);
      }
      if (difficulty.get(Obstacle.PIT)) {
        backgroundImage = overlay(backgroundImage, IMAGE_PATH + PIT_FILE_NAME, 3);
      }
      if (difficulty.get(Obstacle.BAT)) {
        backgroundImage = overlay(backgroundImage, IMAGE_PATH + BAT_FILE_NAME, 0);
      }
      if (smell != null) {
        if (smell[1]) {
          backgroundImage = overlay(backgroundImage, IMAGE_PATH + BREEZE_FILE_NAME, 0);
        }
      }
      background[currentId] = backgroundImage;
    } catch (Exception e) {
      e.printStackTrace();
    }
    explored[currentId] = true;
  }

  @Override
  public void refresh() {
    int currentId = model.getPlayerCurrentLocationId();
    int previousId = model.getPlayerPreviousLocationId();
    if (!explored[currentId]) {
      refreshUnexploredBackground(currentId);
    }
    BufferedImage currentImage = background[currentId];
    if (currentId == model.getPlayerCurrentLocationId(model.whoIsNext())) {
      try {
        currentImage = overlay(currentImage,
                IMAGE_PATH + PLAYERS_FILE_NAME[model.whoIsNext()],
                getOffset(model.whoIsNext()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      currentImage = overlay(currentImage,
              IMAGE_PATH + PLAYERS_FILE_NAME[model.getTurn()],
              getOffset(model.getTurn()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    mazeLabels[currentId].setIcon(new ImageIcon(currentImage));
    if (previousId != -1) {
      BufferedImage previousImage = background[previousId];
      if (model.getTurn() != model.whoIsNext()
              && previousId == model.getPlayerCurrentLocationId(model.whoIsNext())) {
        try {
          previousImage = overlay(previousImage,
                  IMAGE_PATH + PLAYERS_FILE_NAME[model.whoIsNext()],
                  getOffset(model.whoIsNext()));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      mazeLabels[previousId].setIcon(new ImageIcon(previousImage));
    }
    mazePanel.repaint();
    pack();
  }

  private int getOffset(int id) {
    return id * 10 + 10;
  }

  @Override
  public void refreshAfterDie() {
    int currentId = model.getPlayerCurrentLocationId();
    BufferedImage currentImage = background[currentId];
    if (model.getTurn() != model.whoIsNext()
            && currentId == model.getPlayerCurrentLocationId(model.whoIsNext())) {
      try {
        currentImage = overlay(currentImage,
                IMAGE_PATH + PLAYERS_FILE_NAME[model.whoIsNext()],
                getOffset(model.whoIsNext()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    mazeLabels[currentId].setIcon(new ImageIcon(currentImage));
    pack();
    mazePanel.repaint();
  }

  private BufferedImage overlay(BufferedImage starting, String filePath, int offset)
          throws IOException {
    URL overlayUrl = getClass().getResource(filePath);
    BufferedImage overlay = ImageIO.read(overlayUrl);
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  @Override
  public void setFeatures(Controller f) {

    stopGame.addActionListener(l -> f.stopGame());
    restartNew.addActionListener(l -> f.restartNewGame());
    restartSame.addActionListener(l -> f.restartSameGame());

    shoot.addActionListener(l -> f.shoot());
    createButton.addActionListener(l -> f.createMaze(
            (String) boxPlayerNumber.getSelectedItem(),
            (String) boxWrappingType.getSelectedItem(),
            textFields[2].getText(),
            textFields[3].getText(),
            textFields[4].getText(),
            textFields[5].getText(),
            textFields[6].getText(),
            textFields[7].getText(),
            textFields[8].getText(),
            textFields[9].getText()
            )
    );
    cancelButton.addActionListener(l -> f.cancelCreation());
  }

  @Override
  public void setMazeFeatures(Controller f) {
    mazePanel.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
          f.move(Direction.NORTH);
        }
        if (e.getKeyChar() == 's') {
          f.move(Direction.SOUTH);
        }
        if (e.getKeyChar() == 'a') {
          f.move(Direction.WEST);
        }
        if (e.getKeyChar() == 'd') {
          f.move(Direction.EAST);
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_UP:
            f.move(Direction.NORTH);
            break;
          case KeyEvent.VK_DOWN:
            f.move(Direction.SOUTH);
            break;
          case KeyEvent.VK_LEFT:
            f.move(Direction.WEST);
            break;
          case KeyEvent.VK_RIGHT:
            f.move(Direction.EAST);
            break;
          default:
            break;
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        return;
      }
    });

    int n = 0;
    if (mazeLabels != null) {
      n = mazeLabels.length;
    }
    for (int i = 0; i < n; i++) {
      int tmpId = i;
      mazeLabels[i].addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          if (SwingUtilities.isLeftMouseButton(e)) {
            f.processClick(tmpId);
          }
        }
      });
      mazeLabels[i].addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          if (e.isPopupTrigger()) {
            maybeShowPopup(e);
          }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
          if (e.isPopupTrigger()) {
            maybeShowPopup(e);
          }
        }

        private void maybeShowPopup(MouseEvent e) {
          f.processPopup(e.getComponent(), tmpId);
        }
      });
    }
  }
}