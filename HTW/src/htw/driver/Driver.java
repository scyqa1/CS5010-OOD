package htw.driver;

import htw.control.*;
import htw.view.*;
import htw.control.*;

/**
 * This class represents a driver which contains the main function.
 */
public class Driver {

	/**
	 * The main function.
	 */
	public static void main(final String[] args) throws IllegalArgumentException {
		
		View view = new Swing("HTW");
	    Controller controller = new SwingController(view);
	    controller.start();
	}
}
