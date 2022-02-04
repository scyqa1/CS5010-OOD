package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import transmission.AutomaticTransmission;

/**
 * A JUnit test class for AutomaticTransmission.
 */
public class AutomaticTransmissionTest {
  private AutomaticTransmission car;
  private AutomaticTransmission increaseSpeedcar;
  private AutomaticTransmission stoppedCar;
  private AutomaticTransmission carWithSpeed1;



  /**
   * Set up methods before test. 
   */
  @Before
    public void setUp() {
    car = new AutomaticTransmission(20, 35, 45, 55, 70);
    car.setSpeed(10);
    
    increaseSpeedcar = new AutomaticTransmission(3, 5, 7, 9, 11);
    
    stoppedCar = new AutomaticTransmission(20, 35, 45, 55, 70);
    
    carWithSpeed1 = new AutomaticTransmission(20, 35, 45, 55, 70);
    carWithSpeed1.setSpeed(1);
  }


  /**
   * test threshold 1 
   */
  @Test
  public void testThresholdOne() {
    assertEquals(20, car.getthreshold1(), 0);
  }
  
  /**
   * test threshold 2
   */  
  @Test
  public void testThresholdTwo() {
    assertEquals(35, car.getthreshold2(), 0);
  }
  
  /**
   * test threshold 3 
   */
  @Test
  public void testThresholdThree() {
    assertEquals(45, car.getthreshold3(), 0);
  }
  
  /**
   * test threshold 4
   */
  @Test
  public void testThresholdFour() {
    assertEquals(55, car.getthreshold4(), 0);
  }
  
  /**
   * test threshold 5
   */
  @Test
  public void testThresholdFive() {
    assertEquals(70, car.getthreshold5(), 0);
  }
  
  /**
   * test speed
   */
  @Test
  public void testSpeed() {
    assertEquals(10, car.getSpeed(), 0);
  }
  
  /**
   * test gear 
   */
  @Test
  public void testGear() {
    assertEquals(1, car.getGear(), 0);
  }
  
  /**
   * test method increaseSpeed()
   */
  @Test
  public void testIncreaseSpeed() {
    assertEquals(increaseSpeedcar, increaseSpeedcar.increaseSpeed());
    assertEquals(2, increaseSpeedcar.getSpeed());
    
    assertEquals(increaseSpeedcar, increaseSpeedcar.increaseSpeed());
    assertEquals(4, increaseSpeedcar.getSpeed());
    
    assertEquals(increaseSpeedcar, increaseSpeedcar.increaseSpeed());
    assertEquals(6, increaseSpeedcar.getSpeed());
    
    assertEquals(increaseSpeedcar, increaseSpeedcar.increaseSpeed());
    assertEquals(8, increaseSpeedcar.getSpeed());
    
    assertEquals(increaseSpeedcar, increaseSpeedcar.increaseSpeed());
    assertEquals(10, increaseSpeedcar.getSpeed());
    
    assertEquals(increaseSpeedcar, increaseSpeedcar.increaseSpeed());
    assertEquals(12, increaseSpeedcar.getSpeed());
  }
  
  /**
   * test method decreaseSpeed()
   */
  @Test
  public void testDecreaseSpeed() {
    assertEquals(car, car.decreaseSpeed());
    assertEquals(8, car.getSpeed());
  }
  
  /**
   * test invalid threshold 1
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholdOne() {
	new AutomaticTransmission(10, 2, 3, 4, 5);
  }
  
  /**
   * test invalid threshold 2
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholdTwo() {
    new AutomaticTransmission(1, 0, 2, 3, 4);
  }
  
  /**
   * test invalid threshold 3
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholdThree() {
    new AutomaticTransmission(1, 2, 0, 3, 4);
  }
  
  /**
   * test invalid threshold 4
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholdFour() {
    new AutomaticTransmission(1, 2, 3, 0, 4);
  }
  
  /**
   * test invalid threshold 5
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholdFive() {
    new AutomaticTransmission(1, 2, 3, 4, 0);
  }
  
  /**
   * test negative threshold 1
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThresholdOne() {
    new AutomaticTransmission(-1, 2, 3, 4, 5);
  }
  
  /**
   * test negative threshold 2
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThresholdTwo() {
    new AutomaticTransmission(1, -1, 2, 3, 4);
  }
  
  /**
   * test negative threshold 3
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThresholdThree() {
    new AutomaticTransmission(1, 5, -6, 7, 10);
  }
  
  /**
   * test negative threshold 4
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThresholdFour() {
    new AutomaticTransmission(1, 5, 6, -7, 10);
  }
  
  /**
   * test negative threshold 5
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThresholdFive() {
    new AutomaticTransmission(1, 5, 6, 7, -10);
  }
  
  /**
   * test negative speed
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSpeed() {
    car.setSpeed(-1);
  }
  
  /**
   * test zero speed with method decreaseSpeed()
   */
  @Test(expected = IllegalStateException.class)
  public void testZeroDecreaseSpeed() {
    stoppedCar.decreaseSpeed();
  }
  
  /**
   * test speed 1 with method decreaseSpeed()
   */
  @Test(expected = IllegalStateException.class)
  public void testAlmostDecreaseSpeed() {
    carWithSpeed1.decreaseSpeed();
  }
  
  /**
   * test method toString()
   */
  @Test
  public void testToString() {
    assertEquals("Transmission (speed = 10, gear = 1)", car.toString());
  }
  
}