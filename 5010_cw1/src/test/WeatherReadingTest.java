package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weather.WeatherReading;

/**
 * A JUnit test class for the WeatherReading class.
 */
public class WeatherReadingTest {

	private WeatherReading oneday;

  @Before
    public void setUp() {

    oneday = new WeatherReading(9, 6, 10, 3);
  }

  @Test
    public void testTemperature() {
    assertEquals(9, oneday.getTemperature(), 0);
  }

  @Test
    public void testDewPoint() {
    assertEquals(6, oneday.getDewPoint(), 0);
  }

  @Test
    public void testWindSpeed() {
    assertEquals(10, oneday.getWindSpeed(), 0);
  }

  @Test
    public void testTotalRain() {
    assertEquals(3, oneday.getTotalRain(), 0);
  }

  @Test
    public void testRelativeHumidity() {
    assertEquals(85, oneday.getRelativeHumidity(), 0);
  }

  @Test
    public void testHeatIndex() {
    assertEquals(33.35, oneday.getHeatIndex(), .01);
  }

  @Test
  public void testWindChill() {
    assertEquals(43.81, oneday.getWindChill(), .01);
  }

  @Test
  public void testString() {
    assertEquals("Reading: T = 9, " + "D = 6, " + "v = 10, rain = 3", oneday.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDewPoint() {
    WeatherReading dp = new WeatherReading(9, 10, 1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidWindSpeed() {
    WeatherReading ws = new WeatherReading(9, 6, -1, 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidTotalRain() {
    WeatherReading tr = new WeatherReading(9, 6, 0, -1);
  }
}