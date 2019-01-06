package ie.gmit.sw;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author g00349976
 * @version 3.2
 *
 *
 *          FileMenu class is about taking in a parsing the files. Has two
 *          methods,
 */

public class FileMenu {
	// Scanner
	static Scanner scanner = new Scanner(System.in);

	// variables for the files imported by user
	private static File MainFile;
	private static File ComparableFile;

	/**
	 * FileChooser(), which takes Prompts the user for the file input. Then calls
	 * the parser() to convert the files into shingled blockingqueues
	 * 
	 * @see parser()
	 */
	public static void FileChooser() throws IOException, InterruptedException {
		System.out.println("Please Select File to compare against");

		// do while that takes in 2 files. files must be acutal files and be imported
		// successfully before the do while will let you continue.
		// please enter full absolute file path for the files your entering.
		do {
			System.out.println("Please enter the first file: ");
			MainFile = new File(scanner.nextLine());
		} while (!MainFile.exists());
		do {
			System.out.println("Please enter the second file: ");
			ComparableFile = new File(scanner.nextLine());
		} while (!ComparableFile.exists());

		// these queues are for the files input by users. calls teh parser method with
		// the file as parameters
		BlockingQueue<String> mainQueue = parser(MainFile);
		BlockingQueue<String> ComparableQueue = parser(ComparableFile);

		System.out.println(mainQueue);
		System.out.println(ComparableQueue);

	}

	/**
	 * @param currentParingFile
	 * @return
	 * @throws IOException 
	 * @throws InterruptedException  got this exception from using the blockingQueues.
	 * 
	 *                              Parser(), takes the files sent to it from the
	 *                              file chooser, breaks them apart word by word,
	 *                              sorts those words into shingles of 3, adds those
	 *                              shingled strings into an arraylist, that
	 *                              arraylist is then read into a temp
	 *                              Blockingqueue. tempblockingqueue is then
	 *                              returned to set the file blockingqueue
	 */
	public static BlockingQueue<String> parser(File currentParingFile) throws IOException, InterruptedException {

		// temp blockingqueue for sending the shingles to. I know its unnecessary to use
		// a linkedBlockinglist, but it was the only was i could get it working
		// unfortunatly.
		BlockingQueue<String> tempQueue = new LinkedBlockingDeque();

		// temp array list for storing the shingles
		ArrayList<String> TempShingle = new ArrayList<>();
		// add to blocking queue instead of array.

		File parsingMain = currentParingFile;
		Scanner scan = new Scanner(parsingMain);

		// while that seperates the file and splits the words into sets of 3.
		while (true) {
			StringBuilder sb = new StringBuilder();

			// reads in 3 words at a time for shingle
			for (int i = 0; i < 3 || !scan.hasNext(); i++) {
				if (scan.hasNext()) {
					String word = scan.next();
					String formattedWord = word.replaceAll(",", "");
					sb.append(String.format("%s%s", formattedWord, i == 2 ? "" : " "));
					// the %s%S sets the first 2 words of the shingle so that
					// there are spaces between them all
				} else
					break; // breaks out of the for
			} // for

			TempShingle.add(sb.toString());

			if (!scan.hasNext())
				break;// breaks out of the while if the file is empty
		} // while

		for (String shingle : TempShingle) {
			tempQueue.put(shingle);
		} // this for adds each shingle to the queue

		return tempQueue;// returns the tempqueue to the blockingqueue in Filechooser
	}
}
