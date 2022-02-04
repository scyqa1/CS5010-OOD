package weather;

//This class gives folks a better understanding of how people actually feel out in their environment by collecting readings from Stevenson Screen shelters 
public class WeatherReading {
  private int temp_cel;
  private int dewPoint;
  private int windSpeed;
  private int totalRain;

  /*
   * Units and parameters:
   * The air temperature in Celsius (While calculating the wind chill, using temperature in degrees Fahrenheit), 
   * the dew point temperature in Celsius which cannot be greater than the air temperature, 
   * the non-negative wind speed in miles per hour, 
   * the non-negative total rain received in millimeters
   */

  public WeatherReading(int temp_cel, int dewPoint, int windSpeed, int totalRain)
            throws IllegalArgumentException {
    if ((temp_cel < dewPoint) || (windSpeed < 0) || (totalRain < 0)) {
      throw new IllegalArgumentException("Negative wind speed or total rain are not supported. \n "
                    + "Air Temperatures lower than dew points are not supported.");
    }
    
    this.temp_cel = temp_cel;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;
  }

  
  /*
   * Get the temperature in degrees Celsius
   * 
   * @return the temperature
   */
  public int getTemperature() {
    return this.temp_cel;
  }

  
  /*
   * Get the dew point
   * 
   * @return the dew point
   */
  public int getDewPoint() {
    return this.dewPoint;
  }

  
  /*
   * Get the wind speed
   * 
   * @return the wind speed
   */
  public int getWindSpeed() {
    return this.windSpeed;
  }

  
  /*
  * Get the total rain
  * 
  * @return the total rain
  */ 
  public int getTotalRain() {
    return this.totalRain;
  }
  
  
  /*
   * Get the relative humidity
   * 
   * According to the formula: D=T-(100-R)/5
   * R will be calculated as following formula derived from the above formula
   * R=100+5D-5T
   * 
   * @return relative humidity
   */
  public int getRelativeHumidity() {
    int humidity = 100 + 5 * this.dewPoint - 5 * this.temp_cel;    
    return humidity;
  }

  /*
   * Get the heat index
   *
   * @return heat index of this weather reading
   */
  
  public double getHeatIndex() {
    int humidity = this.getRelativeHumidity();
    int temp = this.getTemperature();
    double c1 = -8.78469475556;
    double c2 = 1.61139411;
    double c3 = 2.33854883889;
    double c4 = -0.14611605;
    double c5 = -0.012308094;
    double c6 = -0.0164248277778;
    double c7 = 0.002211732;
    double c8 = 0.00072546;
    double c9 = -0.000003582;
    
    double heatIndex = c1 + c2 * temp + c3 * humidity + c4 * temp * humidity + c5 * Math.pow(temp, 2) + c6 * Math.pow(humidity, 2) + c7 * Math.pow(temp, 2) * humidity + c8 * temp * Math.pow(humidity, 2) + c9 * Math.pow(temp, 2) * Math.pow(humidity, 2);
    
    return heatIndex;

  }


  /*
   * Get the wind chill
   * While calculating, using temperature in degrees Fahrenheit
   *
   * @return the wind chill
   */
  public double getWindChill() {
    double temp = (this.temp_cel * 9.0 / 5.0) + 32;
    double windChill = 35.74 + (0.6215 * temp) - (35.75 * Math.pow(this.windSpeed, 0.16)) + (0.4275 * temp * Math.pow(this.windSpeed, .16));

    return windChill;

  }
 
  
  /*
   * Create an output
   */
  public String toString() {
    String output;
    int temp = this.temp_cel;
    int dewPoint = this.dewPoint;
    int windSpeed = this.windSpeed;
    int totalRain = this.totalRain;
    
    output = String.format("Reading: T = %d, D = %d, v = %d, rain = %d", temp, dewPoint, windSpeed, totalRain);
    return output;
  }
}