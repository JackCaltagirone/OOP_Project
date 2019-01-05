package ie.gmit.sw;

/*Imports*/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFileChooser;

/**
 * @author g00349976
 * 
 *         The menu class interacts with the user. Prompts the user for the in
 *         put files.
 * 
 *         the filechooser() brings up a simple JFileChooser that lets the user
 *         select the files to be parsed.
 * 
 *         they are then sent to parser() to be parsed into shingles and then
 *         put into the blocking queue.
 */
public class Menu {

	private static int filecount = 0;

	static BlockingQueue<String> mainQueue = new LinkedBlockingDeque<>();
	static BlockingQueue<String> ComparableQueue = new LinkedBlockingDeque<>();

	public static void UI() throws IOException, InterruptedException {

		if (filecount == 0) {
			System.out.println("Please Select File to compare against");

			FileChooser();

		}
		filecount++;

		if (filecount == 1) {
			System.out.println("Please Select Comparable file");

			FileChooser();
		}

	}

	public static void FileChooser() throws IOException, InterruptedException {

		JFileChooser chooser = new JFileChooser(); // file chooser method

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			File selectedFile = chooser.getSelectedFile();// selects file for
															// later use
			parser(selectedFile);
		} // is the fill selecter window

	}// FileChooser

	/* Parses the file sent from file chooser into shingles */
	private static void parser(File selectedFile) throws IOException, InterruptedException {

		// blocking queue

		ArrayList<String> MainShingle = new ArrayList<>();
		ArrayList<String> CompareShingle = new ArrayList<>();
		// add to blocking queue instead of array.

		File f = selectedFile;
		Scanner scan = new Scanner(f);

		while (true) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < 3 || !scan.hasNext(); i++) {// reads in 3 words
															// at a time for
															// shingle
				if (scan.hasNext()) {
					String file = scan.next();
					sb.append(String.format("%s%s", file, i == 2 ? "" : " "));
					// the %s%S sets the first 2 words of the shingle so that
					// there are spaces between them all
				} else
					break; // breaks out of the for
			}
			if (filecount == 0)
				MainShingle.add(sb.toString());
			else if (filecount == 1) {
				CompareShingle.add(sb.toString());
			}

			if (!scan.hasNext())
				break;// breaks out of the while
		}

		if (filecount == 0) {
			for (String shingle : MainShingle) {
				mainQueue.put(shingle);
			} // this for adds each shingle to the queue

			System.out.println(mainQueue);
			// prints out shingles

			UI();

		} else if (filecount == 1) {
			for (String shingle : CompareShingle) {
				ComparableQueue.put(shingle);
			} // this for adds each shingle to the queue

			System.out.println(ComparableQueue);
			// prints out shingles

			UI();
		}

	}

}// BlockingQueue
