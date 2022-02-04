package maze;

// Note that Player is optional, it's okay
// to move the functionalities to maze!
public class Player {
  static final double COIN_LOSS_RATIO = 0.1f;

  private Location loc;
  private int coinNum;
  
  
  Player() {
	    this.coinNum = 0;
	  }
  
  public Location getLoc() {
	  return this.loc;
  }
  
  public void setLoc(Location loc) {
	    this.loc = loc;

	  }

  
  /**
   * Get current coins.
   *
   * @return current coins.
   */
  public int getCoins() {
	  return this.coinNum;
  }
  
  void pickupCoins(int coins) {
	  if (coins < 0) {
          throw new IllegalArgumentException("coins cannot be negative");
      }
      this.coinNum += coins;
  }
  
  void loseCoins() {
	  this.coinNum *= (100 - 10) / 100.0;
  }
}
