package transmission;

/**
 * This class represents an automatic transmission.
 *
 */
public final class AutomaticTransmission implements Transmission {
  private final int threshold1;
  private final int threshold2;
  private final int threshold3;
  private final int threshold4;
  private final int threshold5;
  private int speed = 0;
  private int gear = 0;


  /**
  * Constructs an automatic transmission.
  * 
  * @param threshold1 switch from 1st to 2nd gear
  * @param threshold2 switch from 2nd to 3rd gear
  * @param threshold3 switch from 3rd to 4th gear
  * @param threshold4 switch from 4th to 5th gear
  * @param threshold5 switch from 5th to 6th gear
  */
  public AutomaticTransmission(int threshold1, 
		  					   int threshold2, 
							   int threshold3, 
							   int threshold4, 
							   int threshold5) 
	  throws IllegalArgumentException {    
        if ((threshold1 >= threshold2) 
           ||  (threshold2 >= threshold3) 
           || (threshold3 >= threshold4) 
           || (threshold4 >= threshold5) 
           || (threshold1 <= 0)) {
      throw new IllegalArgumentException("Thresholds must increase. \n ");
    }
    this.threshold1 = threshold1;
    this.threshold2 = threshold2;
    this.threshold3 = threshold3;
    this.threshold4 = threshold4;
    this.threshold5 = threshold5;

    this.gear = this.gearBySpeed();
      
  }
  
  /**
   * set the speed of the automatic transmission car.
   */
  public void setSpeed(int speed)throws IllegalArgumentException 
    {if(speed < 0) 
      {throw new IllegalArgumentException("Thresholds must increase. \n ");}
	this.speed = speed;
	this.gear = this.gearBySpeed();
  }
  
  
  /**
   * Return the 1st threshold of the automatic transmission.
   * 
   * @return the 1st threshold of the automatic transmission
   */
  public int getthreshold1() {
    return this.threshold1;
  }
  
  /**
  * Return the 2nd threshold of the automatic transmission.
  * 
  * @return the 2nd threshold of the automatic transmission
  */
  public int getthreshold2() {
    return this.threshold2;
  }
  
  
  /**
  * Return the 3rd threshold of the automatic transmission.
  * 
  * @return the 3rd threshold of the automatic transmission
  */
  public int getthreshold3() {
    return this.threshold3;
  }
  
  /**
  * Return the 4th threshold of the automatic transmission.
  * 
  * @return the 4th threshold of the automatic transmission
  */
  public int getthreshold4() {
    return this.threshold4;
  }
  
  /**
  * Return the 5th threshold of the automatic transmission.
  * 
  * @return the 5th threshold of the automatic transmission
  */
  public int getthreshold5() {
    return this.threshold5;
  }
  
  /**
  * Return the speed the car is traveling.
  * 
  * @return the speed the car is traveling
  */
  public int getSpeed() {
    return this.speed;
  }
  
  /**
  * Return the gear the transmission is in.
  * 
  * @return the gear the transmission is in
  */
  public int getGear() {
    return this.gear;
  }
  
  
  /**
   * Returns overrode toString() method with the transmissions gear and speed.
   * 
   * @return string containing the transmission's speed and gear
   */
  @Override
  public String toString() {
    String gearSpeed = String.format("Transmission (speed = %d, gear = %d)", this.speed, this.gear);
    return gearSpeed;
  }

  
  /**
  * Return the gear from the speed initialized originally.
  * 
  * @return the gear the transmission is in
  */
  private int gearBySpeed() {
    int speed = this.speed;
    int gear;
      
    if (speed == 0) {
      gear = 0;
    } else if (speed < this.threshold1) {
      gear = 1;
    } else if (speed < this.threshold2) {
      gear = 2;
    } else if (speed < this.threshold3) {
      gear = 3;
    } else if (speed < this.threshold4) {
      gear = 4;
    } else if (speed < this.threshold5) {
      gear = 5;
    } else {
      gear = 6;}
    
    return gear; 
  }
  
  
  

  /**
   * Return the automatic transmission object with +2 speed.
   * 
   * @return the automatic transmission object 
   */
  public AutomaticTransmission increaseSpeed() {
    this.speed += 2;
    this.gear = this.gearBySpeed();
    return this;
  }
  
  
  
  /**
   * Return the automatic transmission object with negative speed.
   * @return
   */
  public AutomaticTransmission decreaseSpeed() throws IllegalStateException {
    if (this.speed == 0) {
      throw new IllegalStateException("Car is already stopped!");    
    }
    if (this.speed == 1) {
        throw new IllegalStateException("Car can't slow down that much!");    
      }
    this.speed -= 2;
    this.gear = this.gearBySpeed();
    return this;
      
  }
  



}