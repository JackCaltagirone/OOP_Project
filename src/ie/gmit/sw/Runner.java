package ie.gmit.sw;

import java.io.IOException;

public class Runner {

	/**
	 * @author g00349976 - Jack Caltagirone
	 * 
	 *         this is the runner class. it starts the program from the console It
	 *         calls the parsing class where the files are selected and parsed into
	 *         shingles
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws IOException, InterruptedException {
		new FileMenu().FileChooser();
	}// main

}
 	